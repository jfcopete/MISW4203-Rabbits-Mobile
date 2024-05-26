package com.example.vinilos_rabbits.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.services.PrizeSerialized
import com.example.vinilos_rabbits.viewmodels.PrizeUiState
import com.example.vinilos_rabbits.viewmodels.PrizeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrizeAutocomplete(
    onSelect: (prizeId: Int) -> Unit,
    isEnable: Boolean,
    defaultValue: Int? = null
){
    val prizeViewModel: PrizeViewModel = viewModel()
    val prizesUiState = prizeViewModel.prizesUiState
    prizeViewModel.getPrizes()

    var expanded by remember { mutableStateOf(false) }
    var options by remember { mutableStateOf<List<PrizeSerialized>>(emptyList()) }
    var prizeSelected by remember {
        mutableStateOf("")
    }

    when (prizesUiState) {
        is PrizeUiState.Error -> { options = emptyList() }
        is PrizeUiState.Loading -> { options = emptyList() }
        is PrizeUiState.Success -> { options = prizesUiState.prizes }
    }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            value = prizeSelected,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded
            )},
            modifier = Modifier.menuAnchor()

        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.name) },
                    onClick = {
                        prizeSelected = item.name
                        expanded = false
                        onSelect(item.id)
                    }
                )
            }
        }
    }
}