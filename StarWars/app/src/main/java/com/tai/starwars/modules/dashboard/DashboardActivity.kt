package com.tai.starwars.modules.dashboard

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tai.starwars.R
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.modules.core.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseActivity(), DashboardContract.View {

    @Inject
    lateinit var mPresenter: DashboardContract.Presenter

    private lateinit var mDashboardListAdapter: DashboardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        AndroidInjection.inject(this)
        mPresenter.subscribe(this)
        mDashboardListAdapter = DashboardListAdapter(mPresenter)
        dashboard_rv.setHasFixedSize(true)
        dashboard_rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        dashboard_rv.adapter = mDashboardListAdapter
        mPresenter.getInfo()

        dashboard_refresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))

        dashboard_refresh.setOnRefreshListener {
            mPresenter.getInfo()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unsubscribe(this)
    }

    override fun displayError() {
        dashboard_refresh.isRefreshing = false
        Snackbar.make(dashboard_refresh, R.string.generic_error, Snackbar.LENGTH_LONG).show()
    }

    override fun displayInformation(allTrips: List<TripBean>) {
        dashboard_refresh.isRefreshing = false
        mDashboardListAdapter.clear()
        mDashboardListAdapter.addInformation(allTrips)
        mDashboardListAdapter.notifyDataSetChanged()
    }

}