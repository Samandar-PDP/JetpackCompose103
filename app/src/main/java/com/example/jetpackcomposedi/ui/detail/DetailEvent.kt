package com.example.jetpackcomposedi.ui.detail

sealed interface DetailEvent {
    data class OnGetProduct(val id:Int): DetailEvent
}