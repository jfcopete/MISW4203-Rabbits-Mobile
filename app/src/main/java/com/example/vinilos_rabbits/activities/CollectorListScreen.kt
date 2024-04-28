package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CollectorListScreen(
    modifier: Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(text = "List of Collectors")
    }
}