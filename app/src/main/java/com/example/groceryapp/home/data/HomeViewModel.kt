package com.example.groceryapp.home.data

import androidx.lifecycle.ViewModel
import com.example.groceryapp.model.Category
import com.example.groceryapp.model.GoodStates
import com.example.groceryapp.repository.GoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository : GoodRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        GoodStates(
            goods = repository.getGoodsByCategory(category = Category.VEGETABLES)
        )
    )

    val uiState: StateFlow<GoodStates> = _uiState.asStateFlow()

    private fun changeCategory(category: Category) {
        _uiState.value = _uiState.value.copy(
            selectedCategory = category,
            goods = repository.getGoodsByCategory(category)
        )
    }
    fun onEvent(
        event: HomeEvent
    ) {
        when (event) {
            is HomeEvent.ChangeCategory -> changeCategory(event.category)
        }
    }

}