package com.tai.starwars.domain.repository

import android.util.Log
import com.tai.starwars.domain.bean.TripBean
import com.tai.starwars.domain.cache.StarWarsDatabase
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class DashboardRepository
@Inject
constructor(retrofit: Retrofit, db: StarWarsDatabase) {

    private var mService: DashboardService
    private var mDao: StarWarsDatabase.CachedResourcesDao? = null

    init {
        mService = retrofit.create(DashboardService::class.java)
        mDao = db.cachedResourcesDao()
    }

    suspend fun getInfo() : Deferred<List<TripBean>> = withContext(Dispatchers.IO)  {
        mService.getInfo()
    }

    suspend fun setCache(resource: List<TripBean>) = withContext(Dispatchers.IO) {
        mDao?.deleteAll()
        mDao?.insertAll(resource)
    }

    fun getUserDetails(id: Int?): Observable<TripBean> {
        return mDao?.getUserDetails(id)!!
    }

    fun getAllTrips(): Observable<List<TripBean>>? {
        return mDao?.getAllTrips()
    }

    private interface DashboardService {
        @GET("trips")
        fun getInfo(): Deferred<List<TripBean>>
    }
}
