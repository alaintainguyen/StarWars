package com.tai.starwars.dagger.components

import android.app.Application

import com.tai.starwars.dagger.modules.NetworkModule
import com.tai.starwars.modules.core.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

interface AndroidTaiComponent: AndroidInjector<DaggerApplication> {

    fun inject(activity: BaseActivity)

    fun inject(application: Application)

    fun inject(networkModule: NetworkModule)

}
