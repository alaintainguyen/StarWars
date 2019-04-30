package com.tai.starwars

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object Utils {

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            ViewMatchers.assertThat(adapter?.itemCount, Matchers.`is`(expectedCount))
        }
    }

    fun setRefreshing(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return action.description
            }

            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun perform(uiController: UiController?, view: View?) {
                action.perform(uiController, view)
            }

        }
    }

}

