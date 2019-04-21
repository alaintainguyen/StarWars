package com.tai.starwars.modules.dashboard

import android.content.Intent
import com.tai.starwars.modules.core.BaseRouter
import com.tai.starwars.modules.userDetails.UserDetailsActivity

class DashboardRouter : BaseRouter(), DashboardContract.Router {

    override fun goToDetails(id: Int, view: DashboardContract.View) {
        val intent = Intent(getActivity(view), UserDetailsActivity::class.java)
        intent.putExtra(UserDetailsActivity.ID, id)
        getActivity(view)?.startActivity(intent)
    }
}
