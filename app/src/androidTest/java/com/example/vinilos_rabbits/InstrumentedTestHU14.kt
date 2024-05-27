package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.vinilos_rabbits.activities.HomeApp
import org.junit.Rule
import org.junit.Test

class InstrumentedTestHU14 {
    val ARTISTA: String = "Artista"
    val RUBEN: String = "Rubén Blades Bellido de Luna"
    val PREMIAR_BUTTON: String = "Premiar"

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun checkAddAward() {

        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()
        rule.onNodeWithTag("artistList").performClick()
        rule.waitForIdle()
        Thread.sleep(1000)
        rule.onNodeWithText(RUBEN).performClick()
        rule.waitForIdle()
        Thread.sleep(1000)
        rule.onNodeWithText(PREMIAR_BUTTON).performClick()
        rule.waitForIdle()
        // No pude darle click al dropdownlist

    }
}