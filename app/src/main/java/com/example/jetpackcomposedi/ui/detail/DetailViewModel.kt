package com.example.jetpackcomposedi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposedi.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> get() = _state


    fun onEvent(event: DetailEvent) {
        if (event is DetailEvent.OnGetProduct) {
            viewModelScope.launch {
                repository.getRemoteProductById(event.id)
                    .onStart {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    .catch {
                        _state.value =
                            _state.value.copy(isLoading = false, message = it.message.toString())
                    }
                    .collect {
                        _state.value = _state.value.copy(success = it.body(), isLoading = false)
                    }
            }
        }
    }
}