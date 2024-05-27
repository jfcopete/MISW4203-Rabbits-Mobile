package com.example.vinilos_rabbits.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vinilos_rabbits.R

@Composable
fun MySnackbar(message: String, showSnackbar: Boolean, modifier: Modifier) {
    if (showSnackbar) {
        Snackbar(
            modifier = modifier,
            action = {
                Row {
                    Icon(
                        Icons.Default.Close, contentDescription = stringResource(R.string.close_notification),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        ) {
            Text(text = message)
        }
    }
}