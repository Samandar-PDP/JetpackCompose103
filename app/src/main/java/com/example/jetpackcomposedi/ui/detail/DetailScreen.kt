package com.example.jetpackcomposedi.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposedi.ui.compnent.Loading
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun DetailScreen(
    uiState: DetailState,
    onEvent: (DetailEvent) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(DetailEvent.OnGetProduct(0))
    }
    if (uiState.isLoading) {
        Loading()
    }
    uiState.success?.let { product ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val painter = rememberCoilPainter(request = product.image)
            Image(
                painter = painter,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineLarge
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.category,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = product.price.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Text(
                    text = product.description,
                    fontSize = 12.sp
                )
            }
        }
    }
}