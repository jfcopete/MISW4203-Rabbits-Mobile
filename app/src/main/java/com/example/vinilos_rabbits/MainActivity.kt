package com.example.vinilos_rabbits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.vinilos_rabbits.activities.HomeApp
import com.example.vinilos_rabbits.ui.theme.Vinilos_rabbitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vinilos_rabbitsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    HomeApp()
                }
            }
        }
    }
}

@Composable
fun Home(name: String, description: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = name,
            modifier = modifier
        )
        Text(
            text = description
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Vinilos_rabbitsTheme {
        Home(stringResource(R.string.welcome_title), stringResource(R.string.welcome_description) )
    }
}