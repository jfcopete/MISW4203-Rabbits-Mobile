package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.vinilos_rabbits.activities.HomeApp
import org.junit.Rule
import org.junit.Test

class InstrumentedTestHU04 {
    val ARTISTA: String = "Artista"
    val SEARCH_BLADES_TEST: String = "Rubén Blades Bellido de Luna"

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun checkArtistList() {

        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()
        rule.onNodeWithContentDescription(ARTISTA).performClick()
        rule.onNodeWithText(ARTISTA).assertExists()
        rule.waitForIdle()
        rule.onNodeWithText(SEARCH_BLADES_TEST).performClick()
        rule.onNodeWithText(SEARCH_BLADES_TEST).assertExists()
    }
}