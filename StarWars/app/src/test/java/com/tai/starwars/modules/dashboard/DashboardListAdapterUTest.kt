package com.tai.starwars.modules.dashboard

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.usecase.DashboardUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class DashboardListAdapterUTest {

    @get:Rule
    val unitOfWorkRule = MockitoJUnit.rule()!!

    @Mock
    private lateinit var mRouter: DashboardContract.Router

    @Mock
    private lateinit var mView: DashboardContract.View

    @Mock
    private lateinit var mDashboardUseCase: DashboardUseCase

    private lateinit var mPresenter: DashboardPresenter
    private lateinit var mAdapter: DashboardListAdapter
    private var mTripBean: ArrayList<TripBean> = arrayListOf()

    @Before
    fun setUp() {
        mPresenter = DashboardPresenter(mRouter, mDashboardUseCase)
        mPresenter.subscribe(mView)
        mAdapter = DashboardListAdapter(mPresenter)

        val tripBean1 = TripBean(1)
        val tripBean2 = TripBean(2)
        val tripBean3 = TripBean(3)

        mTripBean.add(tripBean1)
        mTripBean.add(tripBean2)
        mTripBean.add(tripBean3)
    }

    @Test
    fun itemCount() {
        mAdapter.addInformation(mTripBean)
        assertEquals(3, mAdapter.itemCount)
    }

    @Test
    fun clearList() {
        // Given
        mAdapter.addInformation(mTripBean)

        // When
        mAdapter.clear()

        // Verify
        assertEquals(0, mAdapter.itemCount)
    }

}