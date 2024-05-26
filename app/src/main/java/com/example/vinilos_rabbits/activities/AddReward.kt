package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.components.Calendar
import com.example.vinilos_rabbits.components.MySnackbar
import com.example.vinilos_rabbits.components.PrizeAutocomplete
import com.example.vinilos_rabbits.viewmodels.PrizeToArtistUiState
import com.example.vinilos_rabbits.viewmodels.PrizeViewModel
import kotlinx.coroutines.delay
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReward(
    artistId: Int,
    modifier: Modifier
){
    var prizeId: Int by remember { mutableIntStateOf(0) }
    var showSnackbar by remember {
        mutableStateOf(false)
    }
    var captureState by remember {
        mutableStateOf(false)
    }
    val prizeViewModel: PrizeViewModel = viewModel()
    val prizeToArtistUiState = prizeViewModel.prizeToArtistUiState
    val dateSelected = remember {
        mutableStateOf<Date?>(null)
    }

    val isEnable = prizeToArtistUiState != PrizeToArtistUiState.Loading
                && dateSelected.value != null && prizeId > 0

    when (prizeToArtistUiState) {
        is PrizeToArtistUiState.Success -> {
            if(!showSnackbar && captureState) {
                showSnackbar = true
                captureState = false
            }
        }
        is PrizeToArtistUiState.Loading -> showSnackbar = false
        is PrizeToArtistUiState.Error -> showSnackbar = false
        is PrizeToArtistUiState.Off -> {}
    }

    LaunchedEffect(showSnackbar) {
        if(showSnackbar) {
            delay(3000)
            showSnackbar = false
        }
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
        Icon(Icons.Default.Star, contentDescription = stringResource(R.string.aria_prize_icon), Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(24.dp))
        PrizeAutocomplete(
            onSelect = { prizeId = it},
            isEnable = prizeToArtistUiState != PrizeToArtistUiState.Loading
        )
        Spacer(modifier = Modifier.height(16.dp))
        Calendar(
            contentDescription = stringResource(R.string.aria_calendar_prize),
            onDateSelected = { dateSelected.value = it },
            isEnable = prizeToArtistUiState != PrizeToArtistUiState.Loading
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                    prizeViewModel.addPrizeToArtist(
                        prizeId,
                        artistId,
                        premiationDate=dateSelected.value as Date,
                    )
                captureState = true
            },
            enabled = isEnable,

        ) {
            Text(
                text = stringResource(R.string.add_reward_button),
            )
        }
        MySnackbar(
            message = stringResource(id = R.string.added_reward),
            showSnackbar = showSnackbar,
            modifier = Modifier
                .background(Color.Green)
                .align(Alignment.End)
        )
    }
}

