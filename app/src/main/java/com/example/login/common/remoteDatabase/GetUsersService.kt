package com.example.login.common.remoteDatabase

import com.example.login.common.utils.Constants
import com.example.login.common.entities.UserEntity
import retrofit2.http.GET

interface GetUsersService {


    @GET(Constants.GET_USERS_PATH)
    suspend fun getUsers(): UserEntity
}