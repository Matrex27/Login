package com.example.login.common

import com.google.gson.annotations.SerializedName


data class ResponseLogin(@SerializedName("token")var token: String?)