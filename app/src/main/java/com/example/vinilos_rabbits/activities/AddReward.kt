package com.example.vinilos_rabbits.activities

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun AddReward(
    modifier: Modifier
){
    var value by remember { mutableStateOf("") }

    TextField(value = value, onValueChange = { value = it })
}