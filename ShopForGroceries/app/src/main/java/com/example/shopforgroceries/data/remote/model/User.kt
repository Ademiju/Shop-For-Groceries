package com.example.shopforgroceries.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("_id")
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String

        )

