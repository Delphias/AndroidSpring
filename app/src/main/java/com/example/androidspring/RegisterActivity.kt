package com.example.androidspring

import android.content.Intent
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
    private lateinit var password: String
    private lateinit var userName: String
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
//  the super class keyword calls the superclass constructor and should be executed first
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

//findViewById binds a view (textview, buttons, checkbox) in an Android layout(the .xml file) to a field in an Android activity or fragment (the class).
        etUsername= findViewById(R.id.etRUserName)
        etPassword= findViewById(R.id.etRPassword)
        buttonRegister= findViewById(R.id.btnRegister)

// buttonRegister.setOnClickListener{
// declare as a global variable when testing with Toast
// lateinit var userName : String
// It gets user input and store it in the variable userName
// var userName = etUsername.text.toString()
// Used to display a short time notification to the user without affecting the user interaction with UI. The message displayed using Toast class displays quickly, and it disappears after some time
// Toast.makeText(this,  userName + " has been registered", Toast.LENGTH_SHORT).show();


// setting up the button to listen when it's clicked.
        buttonRegister.setOnClickListener{

// calling the method registerUser
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
                if (s == "SUCCESS") {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Successfully registered. Please login",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(this@RegisterActivity, "User already exists!", Toast.LENGTH_LONG)
                        .show()
                }
            }
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })

}
}