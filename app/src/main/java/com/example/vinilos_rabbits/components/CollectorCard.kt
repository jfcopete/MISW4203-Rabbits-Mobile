package com.example.vinilos_rabbits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.viewmodels.ArtistUiState


@Composable
fun CollectorCard(
    name: String,
    email: String,
    onCollectorDetails: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onCollectorDetails)
    ) {
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(text = email, fontSize = 18.sp)
    }
}