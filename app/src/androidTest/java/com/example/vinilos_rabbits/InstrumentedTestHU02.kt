package com.example.vinilos_rabbits
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.vinilos_rabbits.activities.HomeApp


import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
class InstrumentedTestHU02 {

    @get:Rule
    val rule = createComposeRule()

    /**Test título de aplicación renderizado**/

    /**Test albums de prueba renderizados**/
    @Test
    fun checkAlbumsDisplayedDetails() {

        rule.setContent {
            HomeApp()
        }
        rule.waitForIdle()
        rule.onNodeWithText("Vinilos rabbits").assertExists()
        Thread.sleep(2000)
        rule.waitForIdle()
        rule.onNodeWithText("Buscando América").assertExists()
        rule.onNodeWithText("Poeta del pueblo").assertExists()
        rule.onNodeWithText("Buscando América").performClick()
        rule.waitForIdle()
        Thread.sleep(1000)
        rule.onNodeWithText("Canciones").assertExists()
    }
}

