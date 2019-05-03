package com.tai.starwars.modules.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.tai.starwars.R
import com.tai.starwars.Utils
import com.tai.starwars.Utils.setRefreshing
import com.tai.starwars.WaitActivityIsResumedIdlingResource
import com.tai.starwars.modules.userDetails.UserDetailsActivity
import junit.framework.Assert.assertFalse
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardActivityTest {

    @get:Rule
    val mIntentTestRule = IntentsTestRule(DashboardActivity::class.java)

    private var mIdlingResource: IdlingResource? = null

    @Before
    fun setup() {
        mIdlingResource = WaitActivityIsResumedIdlingResource(UserDetailsActivity::class.java.name)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(mIdlingResource)
    }

    @Test
    fun dashboard_onRecyclerView_click() {
        Thread.sleep(3000)
        onView(allOf(withId(R.id.dashboard_rv), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Then
        IdlingRegistry.getInstance().register(mIdlingResource)
        intended(hasComponent(UserDetailsActivity::class.java.name))
    }

    @Test
    fun dashboard_RecyclerItemCount() {
        Thread.sleep(3000)
        onView(withId(R.id.dashboard_rv)).check(Utils.RecyclerViewItemCountAssertion(6))
    }

    @Test
    fun onSwipeRefresh_showList() {
        Thread.sleep(3000)
        val swipeRefresh = mIntentTestRule.activity.findViewById(R.id.dashboard_refresh) as SwipeRefreshLayout
        assertFalse(swipeRefresh.isRefreshing)
        onView(withId(R.id.dashboard_refresh)).perform(setRefreshing(swipeDown(), isDisplayingAtLeast(100)))
        onView(withId(R.id.dashboard_rv)).check(Utils.RecyclerViewItemCountAssertion(6))
        assertFalse(swipeRefresh.isRefreshing)
    }
}