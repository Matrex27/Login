package com.example.login.common.remoteDatabase

import com.example.login.common.entities.RequestLogin
import com.example.login.common.entities.ResponseLogin
import com.example.login.common.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {




    @POST(Constants.LOGIN_PATH)
    suspend fun loginUser(@Body requestLogin: RequestLogin): Response<ResponseLogin>



}




