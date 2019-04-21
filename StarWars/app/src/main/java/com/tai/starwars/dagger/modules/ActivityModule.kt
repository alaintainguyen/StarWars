package com.tai.starwars.dagger.modules


import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.modules.core.BaseActivity

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
class ActivityModule(activity: BaseActivity) {
    private val mActivity: AppCompatActivity

    init {
        mActivity = activity
    }

    /**
     * Expose the activity to dependents in the graph.
     */

    @Provides
    @PerActivity
    internal fun activity(): AppCompatActivity {
        return mActivity
    }
}
