package com.example.login.usersModule

import com.example.login.common.entities.UserEntity
import com.example.login.common.remoteDatabase.GetUsersService
import com.example.login.common.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserIInteractor {

    private var retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GetUsersService::class.java)



    suspend fun getAllUsers(): UserEntity{
        var users  = retrofit.getUsers()
        return users
    }
}