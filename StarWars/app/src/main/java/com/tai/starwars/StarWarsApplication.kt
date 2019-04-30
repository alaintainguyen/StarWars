package com.tai.starwars

import android.app.Activity
import androidx.annotation.VisibleForTesting
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import com.tai.starwars.dagger.components.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class StarWarsApplication : DaggerApplication() {

    @Inject
    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    lateinit var mActivityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    var applicationComponent: ApplicationComponent

    init {
        applicationComponent = initializeApplicationComponent()
        applicationComponent.inject(this)
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())

        Fresco.initialize(this)
    }

    private fun initializeApplicationComponent(): ApplicationComponent {
        return ApplicationComponent.Initializer.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return applicationComponent
    }

}