package com.example.jetpackcomposedi.ui.detail

import com.example.jetpackcomposedi.model.Product

data class DetailState(
    val isLoading: Boolean = false,
    val message: String = "",
    val success: Product? = null
)