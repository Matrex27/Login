package com.example.login.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.login.R
import com.example.login.common.entities.RequestLogin
import com.example.login.common.entities.ResponseLogin
import com.example.login.databinding.ActivityMainBinding
import com.example.login.usersModule.UserViewModel
import com.example.login.usersModule.UsersFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var ViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        isLoginOrRegister()




        binding.loginBtn.setOnClickListener {
            //request que se pasa tanto para login como para register
            var request = RequestLogin(email = binding.emailEt.text.toString(), password = binding.passwordEt.text.toString())


            var isLogin = ViewModel.getIsLoginMode()
            if(isLogin.value == true){
                login(request) //Si la variable live data isLoginMode es true
            }else {
                register(request) //Si la variable live data isLoginMode es false
            }

        }
    }


    private fun setUpViewModel() {
        ViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        ViewModel.setIsLoginMode(true)
    }

    private fun  launchFragment(){

        var userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.getAllUSer()


        val fragment = UsersFragment()
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.mainActivity, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


    }

    //Funcion que cambia el login mode con un onClickListener en el Switch
    private fun isLoginOrRegister(){
        binding.modeSwitch.setOnClickListener {
            if(binding.modeSwitch.isChecked){
                ViewModel.setIsLoginMode(false)
                binding.tvMode.text = getString(R.string.register_switch_mode)
                binding.loginBtn.text = getString(R.string.register_switch_mode)

            }else if(!binding.modeSwitch.isChecked){
                ViewModel.setIsLoginMode(true)
                binding.tvMode.text = getString(R.string.login_witch_mode)
                binding.loginBtn.text = getString(R.string.login_witch_mode)
            }
        }
    }

    //Funcion de login
    //1. Hace la peticion login al view model
    //2. Observa el resultado que se setea en el view model y en caso de exito lo mustra en un Toast,
    //en caso de error tambien lo muestre
    //3. Resetea el resultado para que al llamar al metodo de nuevo no muestre resultados anteriores
    private fun login (requestLogin: RequestLogin){

        ViewModel.login(requestLogin)
        ViewModel.getLoginResult().observe(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Token: ${it.body()?.token.toString()}", Toast.LENGTH_SHORT).show()
                launchFragment()
                binding.loginBtn.visibility = View.GONE


            }else{
                it.errorBody()?.string()
                Toast.makeText(this, getString(R.string.misising_password_error), Toast.LENGTH_SHORT).show()

            }

            ViewModel.resetLoginresult()

        }
    }

    //Funcion de registro
    //1. Hace la peticion login al view model
    //2. Observa el resultado que se setea en el view model y en caso de exito lo muestra en un Toast,
    //en caso de error tambien lo muestra
    //3. Resetea el resultado para que al llamar al metodo de nuevo no muestre resultados anteriores
    private fun register(requestLogin: RequestLogin){
        ViewModel.register(requestLogin)
        ViewModel.getRegisterResult().observe(this){ response ->
            if(response.isSuccessful){
                Toast.makeText(this, "id: ${response.body()?.id } token: ${response.body()?.token}", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, getString(R.string.misising_password_error), Toast.LENGTH_SHORT).show()
            }
            ViewModel.resetRegisterResult()
        }
    }
}