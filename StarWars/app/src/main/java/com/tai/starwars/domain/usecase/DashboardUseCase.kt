package com.tai.starwars.domain.usecase

import com.tai.starwars.dagger.scope.PerActivity
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.repository.DashboardRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

@PerActivity
class DashboardUseCase

@Inject
internal constructor(postExecutionThread: Scheduler, private val mRepository: DashboardRepository) : UseCase<List<TripBean>, Void>(postExecutionThread) {

    override fun buildObservable(params: Void?): Observable<List<TripBean>> {

        return mRepository.getInfo().flatMap { resource ->
            mRepository.setCache(resource.distinctBy {
                r -> r.id
            })
            mRepository.getAllTrips()
        }
    }

}