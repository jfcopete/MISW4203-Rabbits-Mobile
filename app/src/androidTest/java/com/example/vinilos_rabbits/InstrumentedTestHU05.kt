package com.example.vinilos_rabbits

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.vinilos_rabbits.activities.HomeApp
import org.junit.Rule
import org.junit.Test

class InstrumentedTestHU05 {
    val COLECCIONISTA_LIST: String = "collectorList"

    @get:Rule
    val rule = createComposeRule()


    @Test
    fun checkCollectorsList() {
        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()

        // Esperar hasta que el nodo con el TestTag 'collectorList' esté disponible
        // Use useUnmergedTree = true to find the node in the unmerged tree
        rule.onNodeWithTag(COLECCIONISTA_LIST, useUnmergedTree = true).performClick()
    }
}