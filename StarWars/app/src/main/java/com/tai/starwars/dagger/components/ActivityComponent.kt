package com.tai.starwars.dagger.components

import com.tai.starwars.dagger.modules.ActivityModule
import com.tai.starwars.dagger.scope.PerActivity

import androidx.appcompat.app.AppCompatActivity
import dagger.Component

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 *
 * Subtypes of ActivityComponent should be decorated with annotation @PerActivity
 */

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    // Expose those object to sub-graphs
    fun activity(): AppCompatActivity
}
