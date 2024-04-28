package com.example.vinilos_rabbits

import androidx.test.espresso.Espresso.onView
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_rabbits.activities.HomeScreen
import com.example.vinilos_rabbits.services.Comment
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.PerformerSerialized
import com.example.vinilos_rabbits.services.TrackSerialized
import com.example.vinilos_rabbits.viewmodels.HomeUiState

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
@LargeTest
class InstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.vinilos_rabbits", appContext.packageName)
    }
    @Test
    fun checkWelcomeTextDisplayed() {
        Thread.sleep(5000)
        onView(withText("Vinilos rabbits")).check(matches(isDisplayed()))
        Thread.sleep(5000)
    }

    @Test
    fun albumsListDisplayedWithAtLeastTwoAlbums() {
        // Simulamos la lista de álbumes con al menos dos elementos
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
        // Configuramos el estado de la pantalla Home
        composeTestRule.setContent {
            HomeScreen(
                onAlbumDetails = {},
                albumUiState = HomeUiState.Success(albums)
            )
        }
        onView(withText("Description 1")).check(matches(isDisplayed()))
        onView(withText("Description 2")).check(matches(isDisplayed()))
    }
}