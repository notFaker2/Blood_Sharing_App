package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var requestButton: Button
    private lateinit var postButton: TextView
    /*private lateinit var editProfile: TextView
    private lateinit var exit:TextView*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        requestButton=findViewById(R.id.request_button)
        postButton=findViewById(R.id.post)
        /*editProfile=findViewById(R.id.edit_profile)
        exit=findViewById(R.id.exit)*/

        requestButton.setOnClickListener{
            startActivity(Intent(this@ProfileActivity, PostActivity::class.java))
        }
        postButton.setOnClickListener{
            startActivity(Intent(this@ProfileActivity, PostActivity::class.java))
        }
       /* editProfile.setOnClickListener{
            startActivity(Intent(this@ProfileActivity, UpdateProfileActivity::class.java))
        }
        exit.setOnClickListener{
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
        }*/
    }
}