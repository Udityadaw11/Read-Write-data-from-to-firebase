package com.example.uiwithdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signupButton = findViewById<TextView>(R.id.textSignedUp)

        signupButton.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
    }
}