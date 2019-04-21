package com.tai.starwars.domain.bean

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "starwars")
data class TripBean(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Int = 0,

        @ColumnInfo(name = "duration")
        @SerializedName("duration")
        var duration: Long = 0,

        @Embedded
        @SerializedName("distance")
        var distance: DistanceBean? = null,

        @Embedded
        @SerializedName("pilot")
        var pilot: PilotBean? = null,

        @Embedded
        @SerializedName("pick_up")
        var pickUp: PickUpBean? = null,

        @Embedded
        @SerializedName("drop_off")
        var dropOff: DropOffBean? = null

)