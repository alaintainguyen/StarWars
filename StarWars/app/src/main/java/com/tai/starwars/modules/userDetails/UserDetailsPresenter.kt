package com.tai.starwars.modules.userDetails

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.repository.DashboardRepository
import com.tai.starwars.modules.core.BaseContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.observers.ResourceObserver
import io.reactivex.schedulers.Schedulers

class UserDetailsPresenter(private val mRepository: DashboardRepository) : UserDetailsContract.Presenter {

    private var mView: UserDetailsContract.View? = null
    private lateinit var mSubscription: Disposable

    override fun subscribe(view: BaseContract.View) {
        mView = view as UserDetailsContract.View
    }

    override fun unsubscribe(view: BaseContract.View) {
        if (mView == view) {
            mView = null
        }
    }

    override fun getDetailInformation(userId: Int) {
        mSubscription = mRepository.getUserDetails(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(GetDetailsSubscriber())
    }

    inner class GetDetailsSubscriber : ResourceObserver<TripBean>() {

        override fun onNext(@NonNull result: TripBean) {
            mView?.displayUserDetails(result)
        }

        override fun onError(@NonNull e: Throwable) {
            mView?.displayError()
        }

        override fun onComplete() {
            // Nothing to do
        }
    }

}
