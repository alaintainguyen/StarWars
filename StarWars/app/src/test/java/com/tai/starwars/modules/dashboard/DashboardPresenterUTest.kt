package com.tai.starwars.modules.dashboard

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.usecase.DashboardUseCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class DashboardPresenterUTest {

    @get:Rule
    val unitOfWorkRule = MockitoJUnit.rule()!!

    @Mock
    private lateinit var mRouter: DashboardContract.Router

    @Mock
    private lateinit var mView: DashboardContract.View

    @Mock
    private lateinit var mDashboardUseCase: DashboardUseCase

    private lateinit var mPresenter: DashboardPresenter

    private var mTripBean: ArrayList<TripBean> = arrayListOf()

    @Before
    fun setUp() {
        mPresenter = DashboardPresenter(mRouter, mDashboardUseCase)
        mPresenter.subscribe(mView)

        val tripBean1 = TripBean(1)
        val tripBean2 = TripBean(2)
        val tripBean3 = TripBean(3)

        mTripBean.add(tripBean1)
        mTripBean.add(tripBean2)
        mTripBean.add(tripBean3)
    }

    @After
    fun tearDown() {
        mPresenter.unsubscribe(mView)
    }

    @Test
    fun getInformation_success() {
        Mockito.doAnswer { invocation ->
            val subscriber = invocation.arguments[0] as DashboardPresenter.GetInfoSubscriber
            subscriber.onNext(mTripBean)
            null
        }.`when`(mDashboardUseCase).execute(ArgumentMatchers.any(DashboardPresenter.GetInfoSubscriber::class.java))

        // When
        mPresenter.getInfo()

        // Verify
        verify(mView).displayInformation(mTripBean)
    }

    @Test
    fun getInformation_error() {
        Mockito.doAnswer { invocation ->
            val subscriber = invocation.arguments[0] as DashboardPresenter.GetInfoSubscriber
            subscriber.onError(Throwable())
            null
        }.`when`(mDashboardUseCase).execute(ArgumentMatchers.any(DashboardPresenter.GetInfoSubscriber::class.java))

        // When
        mPresenter.getInfo()

        // Verify
        verify(mView).displayError()
    }

    @Test
    fun goToDetails() {
        // Given
        Mockito.doAnswer { invocation ->
            val subscriber = invocation.arguments[0] as DashboardPresenter.GetInfoSubscriber
            subscriber.onNext(mTripBean)
            null
        }.`when`(mDashboardUseCase).execute(ArgumentMatchers.any(DashboardPresenter.GetInfoSubscriber::class.java))
        mPresenter.getInfo()

        // When
        mPresenter.goToDetails(1)

        // Verify
        verify(mRouter).goToDetails(1, mView)
    }

}

