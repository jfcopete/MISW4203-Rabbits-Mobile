package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.vinilos_rabbits.activities.HomeApp
import org.junit.Rule
import org.junit.Test

class InstrumentedTestHU09 {
    val COLECCIONISTA: String = "Coleccionista"
    val MANOLO: String = "Manolo Bellon"
    val NOMBRE_MANOLO: String = "Nombre: Manolo Bellon"

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun checkCollectorsDetail() {

        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()
        rule.onNodeWithTag("collectorList").performClick()
        rule.waitForIdle()
        rule.onNodeWithText(MANOLO).performClick()
        rule.waitForIdle()
        Thread.sleep(1000)
        rule.onNodeWithText(NOMBRE_MANOLO).assertExists()
    }
}