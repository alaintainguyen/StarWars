package com.tai.starwars.modules.dashboard

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.modules.core.BaseContract

interface DashboardContract {

    interface View : BaseContract.View {
        fun displayInformation(allTrips: List<TripBean>)
        fun displayError()
    }

    interface Presenter : BaseContract.Presenter {
        fun getInfo()
        fun goToDetails(id: Int)
    }

    interface Router : BaseContract.Router {
        fun goToDetails(id: Int, view: View)
    }

}
