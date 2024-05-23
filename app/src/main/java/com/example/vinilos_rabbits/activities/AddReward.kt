package com.example.vinilos_rabbits.activities

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.components.PrizeAutocomplete
import com.example.vinilos_rabbits.viewmodels.PrizeViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReward(
    modifier: Modifier
){
    var year by remember { mutableStateOf("") }
    var prizeId: Int by remember { mutableIntStateOf(0) }
    var showSnackbar by remember {
        mutableStateOf(false)
    }
    val prizeViewModel: PrizeViewModel = viewModel()
    val prizeToArtistUiState = prizeViewModel.prizeToArtistUiState

    var dateResult by remember {
        mutableStateOf("Fecha")
    }
    val openDatePickerDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.add_reward_to_artist),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        Icon(Icons.Default.Star, contentDescription = "Reward icon", Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(24.dp))
        PrizeAutocomplete(onSelect = { prizeId = it})
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dateResult,
            onValueChange = {
                openDatePickerDialog.value = true
                var date = "No Selection"
            }
        )
        if (openDatePickerDialog.value) {

        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            Log.e("****", year)
           //prizeViewModel.addPrizeToArtist(prizeId=prizeId, artistId = 100)
        }) {
            Text(text = stringResource(R.string.add_reward))
        }
        MySnackbar(
            message = "show alert",
            showSnackbar = showSnackbar,
            modifier = Modifier
                .background(Color.Green)
                .align(Alignment.End)
        )
    }
}

fun addRewardToDb(){

}

@Composable
fun MySnackbar(message: String, showSnackbar: Boolean, modifier: Modifier) {
    if (showSnackbar) {
        Snackbar(
            modifier = modifier,
            action = {
                Icon(Icons.Default.Close, contentDescription = "close alert")
            }
        ) {
            Text(text = message)
        }
    }
}