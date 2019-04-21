package com.tai.starwars.modules.userDetails

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.domain.usecase.UserDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class UserDetailsActivityModule {

    @Provides
    @PerActivity
    internal fun provideMealDetailsPresenter(userDetailsUseCase: UserDetailsUseCase): UserDetailsContract.Presenter {
        return UserDetailsPresenter(userDetailsUseCase)
    }

}