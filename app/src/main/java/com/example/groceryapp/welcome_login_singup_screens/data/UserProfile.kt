package com.example.groceryapp.welcome_login_singup_screens.data

data class UserProfile(
    val userId: String = "",
    val email: String = "",
    val phone: String = "",
    // Поля ниже заполняются позже
    val name: String? = null,
    val address: String? = null,
    val zipCode: String? = null,
    val city: String? = null,
    val country: String? = null,
    val photoBase64: String? = null // Храним фото как строку
)