package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.android.volley.AuthFailureError
import com.android.volley.Request.Method
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.bloodbank.Utils.Endpoints;
import com.example.bloodbank.Utils.VolleySingleton;
import com.example.bloodbank.R;
import java.util.HashMap
import android.graphics.Color

class LoginActivity : AppCompatActivity() {

    private lateinit var numberEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var submitButton: Button
    private lateinit var signUpText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        numberEt = findViewById(R.id.number)
        passwordEt = findViewById(R.id.password)
        submitButton = findViewById(R.id.submit_button)
        signUpText = findViewById(R.id.signup_text_view)


        signUpText.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        submitButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
            startActivity(intent)  // Start LoginActivity
        }


    }
}
