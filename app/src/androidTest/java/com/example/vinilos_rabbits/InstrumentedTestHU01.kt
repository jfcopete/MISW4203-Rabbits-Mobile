package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.filters.LargeTest
import com.example.vinilos_rabbits.activities.HomeApp
import com.example.vinilos_rabbits.activities.HomeScreen
import com.example.vinilos_rabbits.services.Comment
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.PerformerSerialized
import com.example.vinilos_rabbits.services.TrackSerialized
import com.example.vinilos_rabbits.viewmodels.HomeUiState

import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
class InstrumentedTestHU01 {

    @get:Rule
    val rule = createComposeRule()

    /**Test titulo de aplicacion renderizado**/
    @Test
    fun checkWelcomeTextDisplayed() {
        rule.setContent {
            HomeApp()
        }
        rule.onNodeWithText("Vinilos rabbits").assertExists()
    }

    /**Test albums de prueba renderizados**/
    @Test
    fun checkAlbumsDisplayed() {
        val albums = listOf(
            AlbumSerialized(
                1,
                "Description 1",
                "https://url-to-image-1.com",
                "description",
                "23/07/1992",
                "romantic",
                "sony",
                listOf(TrackSerialized(1,"track1","200"), TrackSerialized(2,"track1","200")),
                listOf(PerformerSerialized(1,"performer1","image","description"), PerformerSerialized(1,"name","image","description")),
                listOf(Comment(1,"rating",1),Comment(2,"rating",1))
            ),
            AlbumSerialized(
                2,
                "Description 2",
                "https://url-to-image-2.com",
                "description",
                "23/07/1992",
                "romantic",
                "sony",
                listOf(TrackSerialized(1,"track1","200"), TrackSerialized(2,"track1","200")),
                listOf(PerformerSerialized(1,"performer1","image","description"), PerformerSerialized(1,"name","image","description")),
                listOf(Comment(1,"rating",1),Comment(2,"rating",1))
            )
        )

        rule.setContent {
            HomeScreen(
                onAlbumDetails = {},
                albumUiState = HomeUiState.Success(albums),

                )
        }
        rule.onNodeWithText("Description 1").assertExists()
        rule.onNodeWithText("Description 2").assertExists()

    }
}

