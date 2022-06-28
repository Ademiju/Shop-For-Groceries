package com.example.shopforgroceries.domain.repositories

import com.example.shopforgroceries.data.remote.request.LoginRequest
import com.example.shopforgroceries.data.remote.response.LoginResponse

interface UserRepository {
    suspend fun login(request: LoginRequest): LoginResponse

}
