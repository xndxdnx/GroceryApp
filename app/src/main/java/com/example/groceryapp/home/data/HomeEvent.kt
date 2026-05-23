package com.example.groceryapp.home.data

import com.example.groceryapp.model.Category

interface HomeEvent {
    data class ChangeCategory (val category: Category) : HomeEvent

    data class SearchQueryChanged(val query: String) : HomeEvent
}