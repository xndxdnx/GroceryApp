package com.example.groceryapp.favorites.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.favorites.repository.FavoritesRepository
import com.example.groceryapp.repository.GoodRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favRepository: FavoritesRepository,
    private val goodRepository: GoodRepository
) : ViewModel(){

    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    fun onToggleFavorite(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite){
                favRepository.deleteFromFavorites(userId = userId.toString(), productId = productId)
            }else{
                favRepository.addToFavorites(userId = userId.toString(), productId = productId)
            }
        }
    }

    val favoriteGoodState = favRepository
        .getAllFavorites(userId = userId.toString())
        .map { idList ->
            idList.mapNotNull { id ->
                goodRepository.getGoodsById(id)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

}