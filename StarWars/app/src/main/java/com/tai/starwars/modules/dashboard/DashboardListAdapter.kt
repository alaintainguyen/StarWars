package com.tai.starwars.modules.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tai.starwars.R
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.modules.utils.BASE_URL
import kotlinx.android.synthetic.main.item_display_info.view.*

class DashboardListAdapter internal constructor(private val mPresenter: DashboardContract.Presenter) : RecyclerView.Adapter<DashboardListAdapter.DashboardViewHolder>() {

    private val mItems: ArrayList<TripBean> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_display_info, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val resultBean = mItems[position]
        holder.itemView.user_name.text = resultBean.pilot?.name
        holder.itemView.user_picture.setImageURI(BASE_URL + resultBean.pilot?.url)
        holder.itemView.user_pick_up.text = resultBean.pickUp?.name
        holder.itemView.user_drop_off.text = resultBean.dropOff?.name

        val rating = resultBean.pilot?.rating?.toInt() ?: 0
        if (rating != 0) {
            holder.itemView.star_group.visibility = VISIBLE
            // We can also custom RatingBar
            holder.itemView.star1.setImageResource(if (rating >= 1) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            holder.itemView.star2.setImageResource(if (rating >= 2) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            holder.itemView.star3.setImageResource(if (rating >= 3) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            holder.itemView.star4.setImageResource(if (rating >= 4) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
            holder.itemView.star5.setImageResource(if (rating >= 5) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
        } else {
            holder.itemView.star_group.visibility = GONE
        }

        holder.itemView.layout.setOnClickListener {
            mPresenter.goToDetails(resultBean.id)
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun addInformation(trips: List<TripBean>) {
        mItems.addAll(trips)
    }

    fun clear() {
        mItems.clear()
    }

    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.user_name
            itemView.user_picture
            itemView.user_pick_up
            itemView.user_drop_off
            itemView.star_group
            itemView.star1
            itemView.star2
            itemView.star3
            itemView.star4
            itemView.star5
            itemView.layout
        }
    }
}
