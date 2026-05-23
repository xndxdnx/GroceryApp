package com.example.groceryapp.cart.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.cart.repository.CartRepository
import com.example.groceryapp.model.CartStates
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
class CartViewModel @Inject constructor(
   private val cartRepository: CartRepository,
   private val goodRepository: GoodRepository
) : ViewModel() {

    private val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

    private val _uiState = MutableStateFlow(CartStates())

    val uiState: StateFlow<CartStates> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cartRepository.getAllCart(userId = userId).collect { cartItems ->

                val uiGoods = cartItems.mapNotNull { item ->
                    val good = goodRepository.getGoodsById(item.productId)
                    if(good != null){
                        CartUiItem (good = good, quantity = item.quantity)
                    }else null
                }
                val total =uiGoods.sumOf { item ->
                    item.good.price * item.quantity
                }

                _uiState.update {  currentState ->
                    currentState.copy(
                        goods = uiGoods,
                        totalPrice = total,
                        isLoading = false
                    )
                }
            }


        }
    }



    fun onChangeQuantity(productId: Int, newQuantity: Int) {
        viewModelScope.launch {
            if (newQuantity <=0){
                cartRepository.deleteFromCart(userId = userId, productId = productId)
            }else{
                cartRepository.addToCart(userId = userId, productId = productId, quantity = newQuantity)
            }
        }

    }

}