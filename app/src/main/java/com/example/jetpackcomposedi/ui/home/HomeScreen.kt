package com.example.jetpackcomposedi.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposedi.ui.compnent.Loading
import com.example.jetpackcomposedi.ui.compnent.ProductItem

@Composable
fun HomeScreen(
    uiState: HomeState,
    onClick: (Int) -> Unit
) {
    if (uiState.isLoading) {
        Loading()
    }
    println("@@@$uiState")
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            uiState.success,
            key = { it.id }
        ) { product ->
            ProductItem(
                product = product,
                onClick = {
                    onClick(product.id)
                }
            )
        }
    }
}