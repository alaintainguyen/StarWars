package com.tai.starwars.modules.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.tai.starwars.R
import com.tai.starwars.modules.userDetails.UserDetailsActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Rule
import org.junit.Test

class DashboardActivityTest {

    @get:Rule
    val testRule = IntentsTestRule(DashboardActivity::class.java)

    @After
    fun tearDown() {
        // IdlingRegistry.getInstance().unregister(WaitActivityIsResumedIdlingResource(DashboardActivity::class.java.name))
    }

    @Test
    fun dashboard_onRecyclerView_click() {
        // Use IdlingResource instead of Thread.sleep
        Thread.sleep(3000)
        //IdlingRegistry.getInstance().register(WaitActivityIsResumedIdlingResource(DashboardActivity::class.java.name))
        onView(allOf(withId(R.id.dashboard_rv), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Then
        intended(hasComponent(UserDetailsActivity::class.java.name))
    }

}