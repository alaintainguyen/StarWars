package com.tai.starwars.dagger.modules

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}
