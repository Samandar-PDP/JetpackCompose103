package com.example.jetpackcomposedi.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposedi.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val TAG = "HomeViewModel"
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state


    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            repository.getRemoteProduct()
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch {
                    _state.value =
                        _state.value.copy(isLoading = false, message = it.message.toString())
                    Log.d(TAG, "loadProducts: ${it.message}")
                }
                .collect {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        success = it.body() ?: emptyList()
                    )
                    Log.d(TAG, "loadProducts: ${it.body()}")
                }
        }
    }
}