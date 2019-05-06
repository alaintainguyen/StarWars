package com.tai.starwars.domain.repository

import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.cache.StarWarsDatabase
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class DashboardRepository

@Inject
constructor(retrofit: Retrofit, db: StarWarsDatabase) {

    private var mService: DashboardService
    private var mDao: StarWarsDatabase.CachedResourcesDao

    init {
        mService = retrofit.create(DashboardService::class.java)
        mDao = db.cachedResourcesDao()
    }

    fun getInfo(): Observable<List<TripBean>> {
        return mService.getInfo()
    }

    fun setCache(resource: List<TripBean>) {
        mDao.deleteAll()
        mDao.insertAll(resource)
    }

    fun getUserDetails(id: Int?): Observable<TripBean> {
        return mDao.getUserDetails(id)
    }

    fun getAllTrips(): Observable<List<TripBean>>? {
        return mDao.getAllTrips()
    }

    private interface DashboardService {
        @GET("trips")
        fun getInfo(): Observable<List<TripBean>>

    }
}
