package com.tai.starwars.modules.userDetails

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.domain.repository.DashboardRepository
import dagger.Module
import dagger.Provides

@Module
class UserDetailsActivityModule {

    @Provides
    @PerActivity
    internal fun provideMealDetailsPresenter(repository: DashboardRepository): UserDetailsContract.Presenter {
        return UserDetailsPresenter(repository)
    }

}