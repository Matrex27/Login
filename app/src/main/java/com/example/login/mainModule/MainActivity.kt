package com.example.login.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.login.R
import com.example.login.common.entities.RequestLogin
import com.example.login.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ViewModel: MainViewModel

    private var isLoginMode = true
    private var isRegisterMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoginOrRegister()
        setUpViewModel()




    }
    //"email": "eve.holt@reqres.in",
    //"password": "cityslicka"
    //erroremail = peter@klaven

    private fun setUpViewModel(){
        ViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    /*
    Este metodo evalua si esta en modo LOGIN o REGISTER para luego hacer la
    respectiva llamada a la API
     */
    private fun isLoginOrRegister(){

        binding.modeSwitch.setOnClickListener {
            if(binding.modeSwitch.isChecked){
                isLoginMode = false
                isRegisterMode = true
                binding.tvMode.text = getString(R.string.register_switch_mode)
                binding.loginBtn.text = getString(R.string.register_switch_mode)
            }else{
                isLoginMode = true
                isRegisterMode = false
                binding.tvMode.text = getString(R.string.login_witch_mode)
                binding.loginBtn.text = getString(R.string.login_witch_mode)
            }
        }
    }


}