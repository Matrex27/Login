package com.example.login.mainModule

import com.example.login.common.remoteDatabase.LoginService
import com.example.login.common.entities.RequestLogin
import com.example.login.common.entities.ResponseLogin
import com.example.login.common.entities.ResponseRegister
import com.example.login.common.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainInteractor {

    private val loginRetrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginService::class.java)



    suspend fun loginUser(requestLogin: RequestLogin): Response<ResponseLogin>{
        return loginRetrofit.loginUser(requestLogin)

    }

    suspend fun registerUser(requestRegister: RequestLogin): Response<ResponseRegister>{
        return  loginRetrofit.registerUser(requestRegister)
    }





}