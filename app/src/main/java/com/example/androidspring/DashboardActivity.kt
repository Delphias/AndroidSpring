package com.example.androidspring

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DashboardActivity : AppCompatActivity() {

    lateinit var welcomeText: String
    lateinit var tvWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        /*
We  Use Intent for Data-Binding and navigating between the Activities or Screens
We need to collect the username from the login json data in the retrofit response
Intent needs to know the parameter we are looking for , in our case "username"
getIntent().getStringExtra finds the  parsed parameters from JSON and binds it to the Screen
*/

//add this inside onCreate under setContentView
        welcomeText ="Welcome "+ getIntent().getStringExtra("Username").toString() + "!";
        tvWelcome = this.findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);

    }
}