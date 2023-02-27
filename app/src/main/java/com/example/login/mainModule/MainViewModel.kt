package com.example.login.mainModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.common.entities.RequestLogin
import com.example.login.common.entities.ResponseLogin
import com.example.login.common.entities.ResponseRegister
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val interactor = MainInteractor()

    private var loginResult = MutableLiveData<Response<ResponseLogin>>()

    private var registerResult = MutableLiveData<Response<ResponseRegister>>()

    private var isLoginMode = MutableLiveData<Boolean>()

    //Metodos Login
    fun getLoginResult():LiveData<Response<ResponseLogin>>{
        return loginResult
    }

    //Soluciona error de mostrar dos veces un resultado
    fun resetLoginresult(){ //Fun reset para dejar los resultados vacios despues de mostrar la informacion
        loginResult = MutableLiveData<Response<ResponseLogin>>()
    }

    //Metodos Register


    fun getRegisterResult(): LiveData<Response<ResponseRegister>>{
        return registerResult
    }


    //Soluciona error de mmostrar dos veces un resultado
    fun resetRegisterResult(){ //Fun reset para dejar los resultados vacios despues de mostrar la informacion
        registerResult = MutableLiveData<Response<ResponseRegister>>()
    }


    //Metodos isLoginMode
    fun  setIsLoginMode(value: Boolean){
        isLoginMode.value = value
    }

    fun getIsLoginMode(): LiveData<Boolean>{
        return isLoginMode
    }


    fun login(requestLogin: RequestLogin){
        viewModelScope.launch {
            try {
                var response = interactor.loginUser(requestLogin)
                loginResult.value = response //Live Data que se observa desde la Main Activity
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun register (requestLogin: RequestLogin){
        viewModelScope.launch{
            try {
                var response = interactor.registerUser(requestLogin)
                registerResult.value = response//Live Data que se observa desde la Main Activity
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }




}