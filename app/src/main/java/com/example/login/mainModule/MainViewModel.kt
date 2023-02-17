package com.example.login.mainModule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.common.RequestLogin
import com.example.login.common.ResponseLogin
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val interactor = MainInteractor()

    private var loginResult = MutableLiveData<Response<ResponseLogin>>()

    fun getResult():LiveData<Response<ResponseLogin>>{
        return loginResult
    }

    fun loginUser(requestLogin: RequestLogin){
        viewModelScope.launch {
            try {
                var response = interactor.loginUser(requestLogin)
                loginResult.value = response
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


}