package com.tai.starwars.dagger.modules

import android.content.Context
import androidx.room.Room
import com.tai.starwars.domain.cache.StarWarsDatabase

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val mContext: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }

    @Provides
    internal fun provideDatabase(): StarWarsDatabase {
        return Room.databaseBuilder(mContext, StarWarsDatabase::class.java, "database-name").build()
    }
}
