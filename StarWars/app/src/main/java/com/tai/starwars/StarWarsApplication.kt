package com.tai.starwars

import android.app.Activity
import androidx.annotation.VisibleForTesting
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import com.tai.starwars.dagger.components.AndroidTaiComponent
import com.tai.starwars.dagger.components.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class StarWarsApplication : DaggerApplication() {

    @Inject
    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    lateinit var mActivityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var applicationComponent: AndroidTaiComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = initializeApplicationComponent()
        applicationComponent.inject(this)

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())

        Fresco.initialize(this)
    }

    private fun initializeApplicationComponent(): AndroidTaiComponent {
        return ApplicationComponent.Initializer.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = ApplicationComponent.Initializer.init(this)
        applicationComponent.inject(this)
        return applicationComponent
    }

}