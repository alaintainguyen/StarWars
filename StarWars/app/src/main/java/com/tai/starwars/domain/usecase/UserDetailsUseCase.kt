package com.tai.starwars.domain.usecase

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.repository.DashboardRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

@PerActivity
class UserDetailsUseCase

@Inject
internal constructor(postExecutionThread: Scheduler, private val mRepository: DashboardRepository) : UseCase<TripBean, Int>(postExecutionThread) {

    override fun buildObservable(params: Int?): Observable<TripBean> {
        return mRepository.getUserDetails(params)
    }

}