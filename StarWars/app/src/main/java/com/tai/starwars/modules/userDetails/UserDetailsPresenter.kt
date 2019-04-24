package com.tai.starwars.modules.userDetails

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.usecase.UserDetailsUseCase
import com.tai.starwars.modules.core.BaseContract
import io.reactivex.annotations.NonNull
import io.reactivex.observers.ResourceObserver

class UserDetailsPresenter(private val mSubDashboardUseCase: UserDetailsUseCase) : UserDetailsContract.Presenter {

    private var mView: UserDetailsContract.View? = null

    override fun subscribe(view: BaseContract.View) {
        mView = view as UserDetailsContract.View
    }

    override fun unsubscribe(view: BaseContract.View) {
        if (mView == view) {
            mView = null
        }
    }

    override fun getDetailInformation(userId: Int) {
        // don't call the other endpoint because it looks the same
        mSubDashboardUseCase.execute(GetDetailsSubscriber(), userId)
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
