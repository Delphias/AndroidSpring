package com.example.androidspring

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RegisterActivity : AppCompatActivity() {
// lateinit allows us to avoid initializing variables when a constructor is  build/created (using the this. keyword to initialize them
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
//  the super class keyword calls the superclass constructor and should be the executed first
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

//finding the assigned values of the set variables
        etUsername= findViewById(R.id.etRUserName)
        etPassword= findViewById(R.id.etRPassword)
        buttonRegister= findViewById(R.id.btnRegister)


        buttonRegister.setOnClickListener{
//            lateinit var userName : String --> declare as a global variable when using Toast
//            var userName = etUsername.text.toString()
//            Toast.makeText(this,  userName + " has been registered", Toast.LENGTH_SHORT).show();

            registerUser()

        }
    }

    private fun registerUser() {

        val userName: String = etUsername.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()
        if (userName.isEmpty()) {
            etUsername.setError("Username is required")
            etUsername.requestFocus()
            return         }
        else if (password.isEmpty()) {
            etPassword.setError("Password is required")
            etPassword.requestFocus()
            return
        }
        val call: Call<ResponseBody> = RetrofitClient
            .getInstance()
            .api
            .createUser(User(userName, password))
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
}
}