package com.example.login.usersModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.common.entities.UserEntity
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {

    private val  interactor = UserIInteractor()


    private var result = MutableLiveData<UserEntity>()

    fun getResult(): LiveData<UserEntity>{
        return result
    }


    fun getAllUSer(){
        viewModelScope.launch {
            try{
                var users = interactor.getAllUsers()
                result.value = users
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }



}