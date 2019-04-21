package com.tai.starwars.domain.repository

import android.content.Context
import androidx.room.Room
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.cache.StarWarsDatabase
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class DashboardRepository

@Inject
constructor(retrofit: Retrofit) {

    private var mService: DashboardService
    private var mDao: StarWarsDatabase.CachedResourcesDao? = null

    init {
        mService = retrofit.create(DashboardService::class.java)
    }

    @Inject
    fun dashboardRepository(context: Context) {
        val db = Room.databaseBuilder(context, StarWarsDatabase::class.java, "database-name").build()
        mDao = db.cachedResourcesDao()
    }

    fun getInfo(): Observable<List<TripBean>> {
        return mService.getInfo()
    }

    fun setCache(resource: List<TripBean>) {
        mDao?.deleteAll()
        mDao?.insertAll(resource)
    }

    fun getUserDetails(id: Int?): Observable<TripBean> {
        return mDao?.getUserDetails(id)!!
    }

    fun getAllTrips(): List<TripBean>? {
        return mDao?.getAllTrips()
    }

    private interface DashboardService {
        @GET("trips")
        fun getInfo(): Observable<List<TripBean>>

    }
}
