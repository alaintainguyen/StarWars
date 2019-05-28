package com.tai.starwars.modules.userDetails

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.modules.core.BaseContract

interface UserDetailsContract {

    interface View : BaseContract.View {
        fun displayUserDetails(result: TripBean)
        fun displayError()
    }

    interface Presenter : BaseContract.Presenter {
        fun getDetailInformation(userId: Int)
        fun convertDateTimeToHoursMinutes(date: String?): String
        fun convertSecondToHoursMinutesSeconds(duration: Long): String
        fun getDistanceValue(): String
    }

}
