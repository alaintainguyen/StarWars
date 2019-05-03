package com.tai.starwars.modules.dashboard

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.repository.DashboardRepository
import com.tai.starwars.modules.core.BaseContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.observers.ResourceObserver
import io.reactivex.schedulers.Schedulers

class DashboardPresenter(private val mRouter: DashboardContract.Router, private val mRepository: DashboardRepository) : DashboardContract.Presenter {

    private var mView: DashboardContract.View? = null
    private lateinit var mSubscription: Disposable

    override fun subscribe(view: BaseContract.View) {
        mView = view as DashboardContract.View
    }

    override fun unsubscribe(view: BaseContract.View) {
        if (mView == view) {
            mView = null
        }
    }

    override fun getInfo() {
        mSubscription = mRepository.getInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(GetInfoSubscriber())
    }

    override fun goToDetails(id: Int) {
        mView?.let { view -> mRouter.goToDetails(id, view) }
    }

    open inner class GetInfoSubscriber : ResourceObserver<List<TripBean>>() {

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