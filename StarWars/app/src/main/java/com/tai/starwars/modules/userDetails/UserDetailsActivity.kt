package com.tai.starwars.modules.userDetails

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.snackbar.Snackbar
import com.tai.starwars.R
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.modules.core.BaseActivity
import com.tai.starwars.modules.utils.DurationUtils.convertDateTimeToHoursMinutes
import com.tai.starwars.modules.utils.DurationUtils.convertSecondToHoursMinutesSeconds
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_details.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class UserDetailsActivity : BaseActivity(), UserDetailsContract.View {

    companion object {
        const val ID: String = "id"
        const val GLOBAL_HTTP: String = "https://backup-star-wars.herokuapp.com"
    }

    @Inject
    lateinit var mPresenter: UserDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        AndroidInjection.inject(this)
        mPresenter.subscribe(this)
        val userId = intent.getIntExtra(ID, -1)

        detail_back.setOnClickListener { finish() }
        mPresenter.getDetailInformation(userId)
    }

    override fun displayUserDetails(result: TripBean) {
        detail_picture.setImageURI(GLOBAL_HTTP + result.pilot?.url)
        detail_pick_up.setImageURI(GLOBAL_HTTP + result.pickUp?.picture)
        detail_drop_off.setImageURI(GLOBAL_HTTP + result.dropOff?.picture)
        detail_name.text = result.pilot?.name
        detail_departure_name.text = result.pickUp?.name
        detail_arrival_name.text = result.dropOff?.name
        detail_departure_time.text = convertDateTimeToHoursMinutes(result.pickUp?.date)
        detail_arrival_time.text = convertDateTimeToHoursMinutes(result.dropOff?.date)
        detail_trip_duration_value.text = convertSecondToHoursMinutesSeconds(result.duration)
        detail_trip_distance_value.text = NumberFormat.getNumberInstance(Locale.US).format(result.distance?.value).plus(" ").plus(result.distance?.unit)

        val rating = result.pilot?.rating?.toInt() ?: 0
        if (rating != 0) {
            detail_star_group.visibility = VISIBLE
            detail_not_rating.visibility = GONE
            // We can also custom RatingBar
            star1.setImageResource(if (rating >= 1) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            star2.setImageResource(if (rating >= 2) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            star3.setImageResource(if (rating >= 3) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            star4.setImageResource(if (rating >= 4) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            star5.setImageResource(if (rating >= 5) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
        } else {
            detail_star_group.visibility = GONE
            detail_not_rating.visibility = VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unsubscribe(this)
    }

    override fun displayError() {
        Snackbar.make(sub_category_cl, R.string.generic_error, Snackbar.LENGTH_LONG).show()
    }

}