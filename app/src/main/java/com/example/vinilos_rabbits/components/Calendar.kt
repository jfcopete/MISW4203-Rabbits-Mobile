package com.example.vinilos_rabbits.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.vinilos_rabbits.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(
    contentDescription: String,
    onDateSelected: (date: Date) -> Unit,
    isEnable: Boolean
){
    val openDatePickerDialog = remember { mutableStateOf(false) }
    var dateResult by remember {
        mutableStateOf("dd-mm-yyyy")
    }
    val datePickerState = rememberDatePickerState()
    val selectedDateMillis = datePickerState.selectedDateMillis

    if (selectedDateMillis != -1L && selectedDateMillis != null) {  // Check if a date is selected
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val instant = Instant.ofEpochMilli(selectedDateMillis)

        val date = instant.atZone(ZoneId.systemDefault())
        dateResult = date.format(formatter)

        val dateFormat = Date.from(instant)
        onDateSelected(dateFormat)
    }
        TextField(
            value = dateResult,
            enabled = isEnable,
            onValueChange = {
                openDatePickerDialog.value = true
            },
            label = { Text(stringResource(R.string.prize_date)) },
            leadingIcon = {
                IconButton(
                    enabled = isEnable,
                    modifier = Modifier.size(48.dp).semantics {  },
                    onClick = { openDatePickerDialog.value = true },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = contentDescription
                    )
                }
            }
        )
    if (openDatePickerDialog.value) {
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                openDatePickerDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDatePickerDialog.value = false
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text(stringResource(R.string.select))
                }
            },
        ){
            DatePicker(state = datePickerState)
        }
    }
}
