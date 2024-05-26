package com.example.vinilos_rabbits.activities

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import com.example.vinilos_rabbits.components.Calendar
import com.example.vinilos_rabbits.components.PrizeAutocomplete
import com.example.vinilos_rabbits.repositories.PrizeRepository
import com.example.vinilos_rabbits.viewmodels.PrizeViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReward(
    modifier: Modifier
){
    var prizeId: Int by remember { mutableIntStateOf(0) }
    var showSnackbar by remember {
        mutableStateOf(false)
    }
    val prizeViewModel: PrizeViewModel = viewModel()
    val prizeToArtistUiState = prizeViewModel.prizeToArtistUiState
    var dateSelected = remember {
        mutableStateOf<Date?>(null)
    }

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
        Calendar(
            contentDescription = stringResource(R.string.aria_calendar_prize),
            onDateSelected = { dateSelected.value = it }
        )
        Button(onClick = {
            Log.i("prize * ", prizeId.toString())
            Log.i("dateSelected * ", dateSelected.value.toString())
            val prizeRepository = PrizeRepository()
            //prizeRepository.addPrizeToArtist()
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