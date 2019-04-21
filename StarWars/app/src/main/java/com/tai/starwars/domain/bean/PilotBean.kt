package com.tai.starwars.domain.bean

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PilotBean(
        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String,

        @ColumnInfo(name = "avatar")
        @SerializedName("avatar")
        var url: String,

        @ColumnInfo(name = "rating")
        @SerializedName("rating")
        var rating: Float
)