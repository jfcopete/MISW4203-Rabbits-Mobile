package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.vinilos_rabbits.activities.HomeApp
import org.junit.Rule
import org.junit.Test

class InstrumentedTestHU03 {
    val artistName: String = "Artista"
    val artistList: String = "artistList"
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun checkArtistList() {

        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()
        rule.onNodeWithTag(artistList).performClick()
        rule.onNodeWithText(artistName).assertExists()
    }

}