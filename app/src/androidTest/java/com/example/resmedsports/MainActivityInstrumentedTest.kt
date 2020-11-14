package com.example.resmedsports

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.resmedsports.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityRecyclerViewTest() {
        onView(withId(R.id.buttonGetResults)).perform(ViewActions.click())

        // Sleep to wait API response
        Thread.sleep(3000)

        onView(withId(R.id.recyclerViewSportResults)).check(
            matches(
                hasChildCount(4)
            )
        )
    }
}