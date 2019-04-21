package com.tai.starwars.modules.dashboard

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.usecase.DashboardUseCase
import com.tai.starwars.modules.core.BaseContract
import io.reactivex.annotations.NonNull
import io.reactivex.observers.ResourceObserver

class DashboardPresenter(private val mRouter: DashboardContract.Router, private val mDashboardUseCase: DashboardUseCase) : DashboardContract.Presenter {

    private var mView: DashboardContract.View? = null

    override fun subscribe(view: BaseContract.View) {
        mView = view as DashboardContract.View
    }

    override fun unsubscribe(view: BaseContract.View) {
        if (mView == view) {
            mView = null
        }
    }

    override fun getInfo() {
        mDashboardUseCase.execute(GetInfoSubscriber())
    }

    override fun goToDetails(id: Int) {
        mView?.let { view -> mRouter.goToDetails(id, view) }
    }

    inner class GetInfoSubscriber : ResourceObserver<List<TripBean>>() {

        override fun onNext(@NonNull resources: List<TripBean>) {
            mView?.displayInformation(resources)
        }

        override fun onError(@NonNull e: Throwable) {
            mView?.displayError()
        }

        override fun onComplete() {
            // Nothing to do
        }
    }

}
