package com.tai.starwars.domain.bean

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class DistanceBean(
        @ColumnInfo(name = "value")
        @SerializedName("value")
        var value: Long,

        @ColumnInfo(name = "unit")
        @SerializedName("unit")
        var unit: String
)