package com.example.jetpackcomposedi.ui.home

import com.example.jetpackcomposedi.model.Product

data class HomeState(
    val isLoading: Boolean = false,
    val message: String = "",
    val success: List<Product> = emptyList()
)