package com.tai.starwars.domain.cache

import androidx.room.*
import com.tai.starwars.domain.bean.TripBean
import io.reactivex.Observable

@Database(entities = [TripBean::class], version = 1, exportSchema = false)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun cachedResourcesDao(): CachedResourcesDao

    @Dao
    interface CachedResourcesDao {

        @Query("SELECT * FROM starwars WHERE id = :id")
        fun getUserDetails(id: Int?): Observable<TripBean>

        @Query("SELECT * FROM starwars")
        fun getAllTrips(): List<TripBean>

        @Insert
        fun insertAll(resources: List<TripBean>)

        @Query("DELETE FROM starwars")
        fun deleteAll()

    }
}
