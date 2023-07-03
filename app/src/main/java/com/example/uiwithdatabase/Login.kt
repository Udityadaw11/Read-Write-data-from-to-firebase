package com.example.uiwithdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//to read data from database
class Login : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference


    // create a companion object
    companion object {
        // we need to create keys for each value we have to display on welcome activity
        const val KEY1 = "com.example.uiwithdatabase.SignUp.mail"  // mail is the name and SignUp is the activity from where the data should be fetched
        const val KEY2 = "com.example.uiwithdatabase.SignUp.name"
        const val KEY3 = "com.example.uiwithdatabase.SignUp.id"
        const val KEY4 = "com.example.uiwithdatabase.SignUp.contact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login1)

        val userName = findViewById<TextInputEditText>(R.id.uniqueUserid)
        val passwordLogin = findViewById<TextInputEditText>(R.id.userPassword)

        loginButton.setOnClickListener {
            //to get value
            val userId = userName.text.toString()
            val password = passwordLogin.text.toString()
            //to read data
            if (userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Enter Username and password", Toast.LENGTH_SHORT)
                    .show()

            }
            else {
                    readData(userId, password)
                    //it send to function readData
            }

        }

        val signupButton = findViewById<TextView>(R.id.textSignedUp)
        signupButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun readData(userId: String, password : String) {
        //operations to read data is performed here
        //initialise lateInit variable
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        //the variable reached the database location, now have check the existence of user id
        databaseReference.child(userId).get().addOnSuccessListener {
            if (it.exists()) {
                //move to new page
                if (password.equals(it.child("passWord").value)){
                    val email = it.child("email").value
                    val name = it.child("name").value
                    val contactNumber = it.child("contact").value
                    val id = it.child("uniqueId").value

                    val intentWelcome = Intent(this, WelcomeActivity::class.java)

                    //to display in the button of welcome activity
                    intentWelcome.putExtra(KEY1, email.toString()) // used to display data on next screen
                    intentWelcome.putExtra(KEY2, name.toString())
                    intentWelcome.putExtra(KEY3, id.toString())
                    intentWelcome.putExtra(KEY4, contactNumber.toString())

                    startActivity(intentWelcome)
                    finish()
                }
                else{
                    Toast.makeText(this,"Password Incorrect", Toast.LENGTH_SHORT).show()
                }

            }
            else {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Error occurs", Toast.LENGTH_SHORT).show()
        }
    }
}
