package com.tai.starwars.dagger.modules

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.modules.dashboard.DashboardActivity
import com.tai.starwars.modules.dashboard.DashboardActivityModule
import com.tai.starwars.modules.userDetails.UserDetailsActivity
import com.tai.starwars.modules.userDetails.UserDetailsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(DashboardActivityModule::class))
    internal abstract fun bindDashboardActivity(): DashboardActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(UserDetailsActivityModule::class))
    internal abstract fun bindSubCategoryActivity(): UserDetailsActivity

}