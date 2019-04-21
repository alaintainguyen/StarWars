package com.tai.starwars.modules.dashboard

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.domain.usecase.DashboardUseCase
import dagger.Module
import dagger.Provides

@Module
class DashboardActivityModule {

    @Provides
    @PerActivity
    internal fun provideDashboardPresenter(router: DashboardContract.Router, dashboardUseCase: DashboardUseCase): DashboardContract.Presenter {
        return DashboardPresenter(router, dashboardUseCase)
    }

    @Provides
    @PerActivity
    internal fun provideDashboardRouter(): DashboardContract.Router {
        return DashboardRouter()
    }

}