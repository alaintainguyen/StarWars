package com.tai.starwars.domain.bean

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class DropOffBean(
        @ColumnInfo(name = "name_drop_off")
        @SerializedName("name")
        var name: String,

        @ColumnInfo(name = "picture_drop_off")
        @SerializedName("picture")
        var picture: String,

        @ColumnInfo(name = "date_drop_off")
        @SerializedName("date")
        var date: String
)