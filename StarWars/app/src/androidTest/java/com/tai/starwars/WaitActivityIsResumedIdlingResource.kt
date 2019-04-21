package com.tai.starwars

import androidx.test.espresso.IdlingResource
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

class WaitActivityIsResumedIdlingResource(private val activityToWaitClassName: String) : IdlingResource {
    private val instance: ActivityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance()

    @Volatile
    private var mResourceCallback: IdlingResource.ResourceCallback? = null

    private val isActivityLaunched: Boolean
        get() {
            val activitiesInStage = instance.getActivitiesInStage(Stage.RESUMED)
            for (activity in activitiesInStage) {
                if (activity.javaClass.name == activityToWaitClassName) {
                    return true
                }
            }
            return false
        }

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        val resumed: Boolean = isActivityLaunched
        if (resumed && mResourceCallback != null) {
            mResourceCallback?.onTransitionToIdle()
        }

        return resumed
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        mResourceCallback = resourceCallback
    }
}
