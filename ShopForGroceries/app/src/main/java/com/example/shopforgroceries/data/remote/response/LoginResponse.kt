package com.example.shopforgroceries.data.remote.response

import com.example.shopforgroceries.data.remote.model.User

data class LoginResponse(
    val status: String,
    val token: String,
    val date: User
)
