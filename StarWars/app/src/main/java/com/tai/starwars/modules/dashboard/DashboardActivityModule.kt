package com.tai.starwars.modules.dashboard

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.domain.repository.DashboardRepository
import dagger.Module
import dagger.Provides

@Module
class DashboardActivityModule {

    @Provides
    @PerActivity
    internal fun provideDashboardPresenter(router: DashboardContract.Router, repository: DashboardRepository): DashboardContract.Presenter {
        return DashboardPresenter(router, repository)
    }

    @Provides
    @PerActivity
    internal fun provideDashboardRouter(): DashboardContract.Router {
        return DashboardRouter()
    }

}