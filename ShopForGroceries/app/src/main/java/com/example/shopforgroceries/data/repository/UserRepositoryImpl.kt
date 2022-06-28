package com.example.shopforgroceries.data.repository

import com.example.shopforgroceries.data.remote.Api
import com.example.shopforgroceries.data.remote.request.LoginRequest
import com.example.shopforgroceries.data.remote.response.LoginResponse
import com.example.shopforgroceries.domain.repositories.UserRepository


class UserRepositoryImpl(
    private val api: Api
): UserRepository{
    override suspend fun login(request: LoginRequest): LoginResponse{
        return api.login(request)
    }
}