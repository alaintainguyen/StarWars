package com.tai.starwars.modules.core

import android.os.Bundle

import com.tai.starwars.StarWarsApplication
import com.tai.starwars.dagger.components.AndroidTaiComponent
import com.tai.starwars.dagger.modules.ActivityModule

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    protected val activityModule: ActivityModule get() = ActivityModule(this)

    protected val applicationComponent: AndroidTaiComponent get() = (application as StarWarsApplication).applicationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
