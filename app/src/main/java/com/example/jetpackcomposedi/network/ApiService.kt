package com.example.jetpackcomposedi.network

import com.example.jetpackcomposedi.model.Product
import com.example.jetpackcomposedi.model.StoreDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<StoreDTO>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Response<Product>
}