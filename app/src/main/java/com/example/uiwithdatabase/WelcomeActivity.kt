package com.example.uiwithdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.uiwithdatabase.databinding.ActivityWelcomeBinding
import org.w3c.dom.Text

class WelcomeActivity : AppCompatActivity() {

    //lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        setContentView(R.layout.activity_welcome)
            //to hide the action bar
       /* supportActionBar?.hide()*/

        // get data through keys created in login activity
        val email = intent.getStringExtra(Login.KEY1)
        val name = intent.getStringExtra(Login.KEY2)
        val id = intent.getStringExtra(Login.KEY3)
        val contact = intent.getStringExtra(Login.KEY4)

        // to show data on text view in welcome activity

        val welcomeText = findViewById<TextView>(R.id.welComeMsg)
        val emailAdrs = findViewById<TextView>(R.id.txtMail)
        val uniqueID = findViewById<TextView>(R.id.txtUserId)
        val phoneNo = findViewById<TextView>(R.id.txtContact)
        //show data
        //we can directly use binding class to call the view
        welcomeText.text= "Welcome $name" // what to show
        emailAdrs.text = "Mail : $email" //msg and variable which are declared above to extract data from login activity
        uniqueID.text = "User ID : $id"
        phoneNo.text = "Contact Number : $contact"

        /*binding.welComeMsg.text = "Welcome $name" // what to show
        binding.txtMail.text = "Mail : $email" //msg and variable which are declared above to extract data from login activity
        binding.txtUserId.text = "User ID : $id"
        binding.txtContact.text = "Contact Number : $contact"*/
    }
}