package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.vinilos_rabbits.activities.HomeApp
import org.junit.Rule
import org.junit.Test

class InstrumentedTestHU04 {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun checkWelcomeTextDisplayed() {

    }

    @Test
    fun checkArtistList() {

        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()
        rule.onNodeWithContentDescription("Artista").performClick()
        rule.onNodeWithText("Artista").assertExists()
        checkArtistisplayedDtails()

    }

    fun checkArtistisplayedDtails(){
        rule.onNodeWithText("").assertExists()
    }


}