package com.example.groceryapp.home.data

import com.example.groceryapp.main_model.Category

interface HomeEvent {
    data class ChangeCategory (val category: Category) : HomeEvent

    data class SearchQueryChanged(val query: String) : HomeEvent
}