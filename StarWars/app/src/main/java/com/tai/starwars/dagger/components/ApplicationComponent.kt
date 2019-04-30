package com.tai.starwars.dagger.components

import android.app.Application
import com.tai.starwars.StarWarsApplication
import com.tai.starwars.dagger.modules.ActivityBuilder
import com.tai.starwars.dagger.modules.ApplicationModule
import com.tai.starwars.dagger.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, NetworkModule::class, ActivityBuilder::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): ApplicationComponent
    }

    fun inject(target: StarWarsApplication)

    class Initializer private constructor() {

        companion object {

            fun init(application: Application): ApplicationComponent {
                return DaggerApplicationComponent.builder()
                        .application(application)
                        .applicationModule(ApplicationModule(application))
                        .networkModule(NetworkModule())
                        .build()
            }
        }
    }
}

