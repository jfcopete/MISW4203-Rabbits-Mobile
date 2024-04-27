package com.example.vinilos_rabbits.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.vinilos_rabbits.R

@Composable
fun Layout() {
    Text(
        text= stringResource(R.string.welcome_title),
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
    )
}