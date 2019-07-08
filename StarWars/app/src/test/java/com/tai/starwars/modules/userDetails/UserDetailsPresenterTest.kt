package com.tai.starwars.modules.userDetails

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.tai.starwars.domain.bean.*
import com.tai.starwars.domain.usecase.UserDetailsUseCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class UserDetailsPresenterTest {

    @get:Rule
    val unitOfWorkRule = MockitoJUnit.rule()!!

    @Mock
    private lateinit var mView: UserDetailsContract.View

    @Mock
    private lateinit var mUserDetailsUseCase: UserDetailsUseCase

    private lateinit var mPresenter: UserDetailsPresenter
    private lateinit var mTripBean: TripBean

    @Before
    fun setUp() {
        mPresenter = UserDetailsPresenter(mUserDetailsUseCase)
        mPresenter.subscribe(mView)

        val distanceBean = DistanceBean(1231244, "KM")
        val pilotBean = PilotBean("Dark Vador", "test", 5F)
        val pickUpBean = PickUpBean("Naboo", "test", "someDate")
        val dropOffBean = DropOffBean("Tatouine", "test", "someDate")
        mTripBean = TripBean(1, 123435, distanceBean, pilotBean, pickUpBean, dropOffBean)
    }

    @After
    fun tearDown() {
        mPresenter.unsubscribe(mView)
    }

    @Test
    fun getUserDetails_success() {
        // Given
        Mockito.doAnswer { invocation ->
            val subscriber = invocation.arguments[0] as UserDetailsPresenter.GetDetailsSubscriber
            subscriber.onNext(mTripBean)
            null
        }.`when`(mUserDetailsUseCase).execute(any<UserDetailsPresenter.GetDetailsSubscriber>(), eq(1))

        // When
        mPresenter.getDetailInformation(1)

        // Verify
        verify(mView).displayUserDetails(mTripBean)
    }

    @Test
    fun getUserDetails_error() {
        // Given
        Mockito.doAnswer { invocation ->
            val subscriber = invocation.arguments[0] as UserDetailsPresenter.GetDetailsSubscriber
            subscriber.onError(Throwable())
            null
        }.`when`(mUserDetailsUseCase).execute(any<UserDetailsPresenter.GetDetailsSubscriber>(), eq(1))

        // When
        mPresenter.getDetailInformation(1)

        // Verify
        verify(mView).displayError()
    }
}