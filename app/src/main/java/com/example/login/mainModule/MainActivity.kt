package com.example.login.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.login.common.LoginService
import com.example.login.common.RequestLogin
import com.example.login.common.ResponseLogin
import com.example.login.common.utils.Constants
import com.example.login.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            loginServiceTest()
        }

        setUpViewModel()


    }

    private fun setUpViewModel(){
        ViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }


    //"email": "eve.holt@reqres.in",
    //"password": "cityslicka"
    //erroremail = peter@klaven

    private fun loginServiceTest(){

        var email = binding.emailET.text.toString()
        var password = binding.passwordET.text.toString()

        ViewModel.loginUser(RequestLogin(email, password))

        ViewModel.getResult().observe(this) {response ->

            if (response.isSuccessful) {
                var succes = response.body().toString()
                println(succes)
            }else {
                var error = response.errorBody()?.string()
                println(error)
            }
        }
    }



}