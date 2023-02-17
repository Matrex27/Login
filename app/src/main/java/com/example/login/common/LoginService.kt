package com.example.login.common

import com.example.login.common.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {




    @POST(Constants.LOGIN_PATH)
    suspend fun loginUser(@Body requestLogin: RequestLogin): Response<ResponseLogin>



}




