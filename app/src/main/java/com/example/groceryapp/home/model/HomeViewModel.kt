package com.example.groceryapp.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.cart.repository.CartRepository
import com.example.groceryapp.favorites.repository.FavoritesRepository
import com.example.groceryapp.home.data.HomeEvent
import com.example.groceryapp.model.Category
import com.example.groceryapp.model.GoodStates
import com.example.groceryapp.repository.GoodRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : GoodRepository,
    private val favRepository: FavoritesRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
    // функция для избранного
    fun onToggleFavorite(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite){
                favRepository.deleteFromFavorites(userId = userId, productId = productId)
            }else{
                favRepository.addToFavorites(userId = userId, productId = productId)
            }
        }
    }
    // Функция для корзины
    fun onChangeQuantity(productId: Int, newQuantity: Int) {
        viewModelScope.launch {
            if (newQuantity <=0){
                cartRepository.deleteFromCart(userId = userId, productId = productId)
            }else{
                cartRepository.addToCart(userId = userId, productId = productId, quantity = newQuantity)
            }
        }

    }
    // локальное хранилище состояний экрана
    private val _uiState = MutableStateFlow(
        GoodStates(goods = repository.getGoodsByCategory(category = Category.VEGETABLES))
    )
    // хранилище для передачи в экран которое дублирует локальное конвертирует
    val uiState: StateFlow<GoodStates> = _uiState.asStateFlow()

    // обрабатываем события репозиториев
    init {
        viewModelScope.launch {
            favRepository.getAllFavorites(userId = userId).collect { listOfIds ->
                _uiState.update { currentState ->
                    currentState.copy(favoriteProductIds = listOfIds)
                }
            }
        }

        viewModelScope.launch {
            cartRepository.getAllCart(userId = userId).collect { cartItems ->
               val cartMap = cartItems.associate { item ->
                   item.productId to item.quantity
               }
                _uiState.update { currentState ->
                    currentState.copy(cartProductsCount = cartMap)
                }
            }
        }
    }

    // сменяем категорию
    private fun changeCategory(category: Category) {

        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category,
                searchQuery = "", // сбрасываем поиск
                goods = repository.getGoodsByCategory(category)
            )
        }

    }

    // функция для поисковой строки
    private fun updateSearchQuery(query: String){
        _uiState.update { it.copy(searchQuery = query) }
        val currentCategory = _uiState.value.selectedCategory

        val allCategoryGoods = repository.getGoodsByCategory(currentCategory)

        val filteredGoods = allCategoryGoods.filter { good ->
            good.title.lowercase().contains(query.lowercase())
        }
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = query,
                goods = filteredGoods
            )
        }
    }



    // главный обработчик состояний экрана
    fun onEvent(
        event: HomeEvent
    ) {
        when (event) {
            is HomeEvent.ChangeCategory -> changeCategory(event.category)

            is HomeEvent.SearchQueryChanged -> updateSearchQuery(query = event.query)
        }
    }

}