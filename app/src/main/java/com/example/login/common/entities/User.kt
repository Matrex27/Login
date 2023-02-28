package com.example.login.common.entities

class User (var id: Long,
            var email: String,
            var first_name: String,
            var last_name: String,
            var avatar: String
            ){

    fun showFullName():  String{
        return "$first_name $last_name"
    }
}

