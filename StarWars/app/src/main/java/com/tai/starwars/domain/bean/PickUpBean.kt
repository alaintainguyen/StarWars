package com.tai.starwars.domain.bean

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PickUpBean(
        @ColumnInfo(name = "name_pick_up")
        @SerializedName("name")
        var name: String,

        @ColumnInfo(name = "picture_pick_up")
        @SerializedName("picture")
        var picture: String,

        @ColumnInfo(name = "date_pick_up")
        @SerializedName("date")
        var date: String
)