package com.example.vinilos_rabbits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.viewmodels.ArtistUiState


@Composable
fun ArtistCard(
    name: String,
    image: String,
    description: String,
    onArtistDetails: () -> Unit
) {
    Surface(
        onClick=onArtistDetails,
    ){
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "Foto del artista",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (description.length > 85) description.take(85) + "..." else description,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.fillMaxWidth(), // Esto hace que el Text ocupe todo el ancho disponible
                        textAlign = TextAlign.Justify // Esto centra el texto horizontalmente
                    )
                }
            }
        }
    }

}
