package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.Comment
import com.example.vinilos_rabbits.services.TrackSerialized
import com.example.vinilos_rabbits.viewmodels.AlbumUiState
import com.example.vinilos_rabbits.viewmodels.AlbumViewModel
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.components.MySnackbar
import com.example.vinilos_rabbits.models.Collector
import kotlinx.coroutines.delay

@Composable
fun AlbumDetails(
    albumId: Int,
    modifier: Modifier = Modifier,
) {
    val albumViewModel: AlbumViewModel = viewModel()
    val albumUiState by albumViewModel.albumUiState.collectAsState()
    val newCommentUiState by albumViewModel.newCommentUiState.collectAsState()
    val error by albumViewModel.error.collectAsState()

    // Llamada a la solicitud
    LaunchedEffect(albumId) {
        albumViewModel.getAlbumById(albumId)
    }

    var showConfirmation by remember { mutableStateOf(false) }

    if (showConfirmation) {
        LaunchedEffect(showConfirmation) {
            delay(3000)
            showConfirmation = false
        }
    }

    Scaffold(
        modifier = modifier,
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                when (albumUiState) {
                    is AlbumUiState.Loading -> LoadingScreen()
                    is AlbumUiState.Error -> Text("Error")
                    is AlbumUiState.Success -> {
                        AlbumDetailsScreen(
                            album = (albumUiState as AlbumUiState.Success).album,
                            albumViewModel = albumViewModel,
                            albumId = albumId,
                            showConfirmation = { showConfirmation = it }
                        )
                    }
                }

                if (showConfirmation) {
                    MySnackbar(
                        message = "Comentario agregado exitosamente",
                        showSnackbar = showConfirmation,
                        modifier = Modifier
                            .background(Color.Green)
                    )
                }

                if (error != null) {
                    Text("Error: $error")
                }
            }
        }
    )
}

@Composable
fun AlbumDetailsScreen(
    album: AlbumSerialized,
    albumViewModel: AlbumViewModel,
    albumId: Int,
    showConfirmation: (Boolean) -> Unit
) {
    var commentText by remember { mutableStateOf("") }
    var isAddingComment by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.padding(1.dp)
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(album.cover),
                contentDescription = album.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = album.name)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = album.description)
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(R.string.songs), style = TextStyle(
                fontSize = 24.sp
            ))
            Tracks(tracks = album.tracks)

            Text(text = stringResource(R.string.comments), style = TextStyle(
                fontSize = 24.sp
            ))
            Comments(comments = album.comments)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿Eres coleccionista?",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        item {
            Switch(
                checked = isAddingComment,
                onCheckedChange = { isAddingComment = it },
                modifier = Modifier.padding(8.dp)
            )
        }

        if (isAddingComment) {
            item {
                AddCommentSection(
                    commentText = commentText,
                    onCommentTextChanged = { commentText = it },
                    onSubmitComment = {
                        if (commentText.isNotEmpty()) {
                            val newComment = com.example.vinilos_rabbits.models.Comment(
                                description = commentText,
                                rating = "5",
                                collector = Collector(
                                    id = 1,
                                    name = "",
                                    telephone = "",
                                    email = ""
                                )
                            )
                            albumViewModel.addCommentToAlbum(albumId, newComment)
                            commentText = ""
                            showConfirmation(true)
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun AddCommentSection(
    commentText: String,
    onCommentTextChanged: (String) -> Unit,
    onSubmitComment: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = commentText,
            onValueChange = onCommentTextChanged,
            label = { Text("Agrega un comentario") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onSubmitComment,
        ) {
            Text("Guardar")
        }
    }
}



@Composable
fun Tracks(tracks: List<TrackSerialized>?) {
    Column {
        tracks?.forEach {
            Row {
                Text(text = it.name)
                Text(text = " - ")
                Text(text = it.duration)
            }
        }
    }
}

@Composable
fun Comments(comments: List<Comment>?) {
    Column {
        comments?.forEach {
            Row(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = it.description)
            }
        }
    }
}
