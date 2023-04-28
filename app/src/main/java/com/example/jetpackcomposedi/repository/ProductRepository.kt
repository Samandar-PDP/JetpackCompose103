package com.example.jetpackcomposedi.repository

import com.example.jetpackcomposedi.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRemoteProduct() = flow { emit(apiService.getProducts()) }

    suspend fun getRemoteProductById(id: Int) = flow { emit(apiService.getProduct(id)) }
}