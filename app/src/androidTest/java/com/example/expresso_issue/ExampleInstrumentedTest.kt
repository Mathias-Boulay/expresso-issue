package com.example.expresso_issue

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun landscapeHandling() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Button triggers an intent to another activity
        onView(withId(R.id.silly_button)).perform(click())

        /*
         * The issue happens here, when trying to interact with the UI
         * If you have the following conditions:
         * - Multi process expresso, with 2 activities (one for each process)
         * - Activity rotations are different (one portrait, one landscape)
         *
         * This case is fairly frequent if you have an activity that requires landscape usage,
         * like controlling a robot/drone with a camera.
         */
        onView(withId(R.id.empty_button)).perform(click())

        scenario.close()
    }
}