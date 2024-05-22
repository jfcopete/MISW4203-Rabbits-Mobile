package com.example.vinilos_rabbits.activities

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.components.PrizeAutocomplete

@Composable
fun AddReward(
    modifier: Modifier
){
    var value by remember { mutableStateOf("") }
    Column {
        Text(text = stringResource(R.string.add_reward_to_artist))
        Icon(Icons.Default.Star, contentDescription = "Reward icon")
        PrizeAutocomplete()
        TextField(value = value, onValueChange = { value = it })
    }
}