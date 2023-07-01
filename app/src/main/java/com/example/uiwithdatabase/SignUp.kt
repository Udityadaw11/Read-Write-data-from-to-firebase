package com.example.uiwithdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    //create a variable of database reference type
    // lateinit var we create a variable whose value will be assigned latter

    lateinit var database : DatabaseReference

    // database reference is a class which is used for storage of data in firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signButton = findViewById<Button>(R.id.signup)

        val etName = findViewById<TextInputEditText>(R.id.userName)
        val etEmail = findViewById<EditText>(R.id.userEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.userPassword)
        val etUserId = findViewById<TextInputEditText>(R.id.uniqueUserid)
        val etPhone = findViewById<TextInputEditText>(R.id.userContact)

        // to store data in database
        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etEmail.text.toString()
            val password = etPassword.text.toString()
            val userId = etUserId.text.toString()
            val phone = etPhone.text.toString()

            //create an object of data class
            val user = User(name, mail, password, userId, phone)
            //the parameters are from set on click listener

            // initialising database
            database = FirebaseDatabase.getInstance().getReference("Users")

            //enter data into firebase
            database.child(userId).setValue(user).addOnSuccessListener {

                // to clear the field
                etName.text?.clear()
                etEmail.text?.clear()
                etPassword.text?.clear()
                etUserId.text?.clear()
                etPhone.text?.clear()

                // to show toast
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT)

                //after sign up move to login page
                val intent = Intent(this,Login::class.java)
                startActivity(intent)



            }
        }

        val loginButton = findViewById<TextView>(R.id.textLogin)

        loginButton.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}