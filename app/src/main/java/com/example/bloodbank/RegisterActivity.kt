package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.bloodbank.Utils.Endpoints
import com.example.bloodbank.Utils.VolleySingleton
import com.example.bloodbank.R;

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEt: EditText
    private lateinit var cityEt: EditText
    private lateinit var bloodGroupEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var mobileEt: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEt = findViewById(R.id.name)
        cityEt = findViewById(R.id.city)
        bloodGroupEt = findViewById(R.id.blood_group)
        passwordEt = findViewById(R.id.password)
        mobileEt = findViewById(R.id.number)
        submitButton = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            val name = nameEt.text.toString()
            val city = cityEt.text.toString()
            val bloodGroup = bloodGroupEt.text.toString()
            val password = passwordEt.text.toString()
            val mobile = mobileEt.text.toString()

            if (isValid(name, city, bloodGroup, password, mobile)) {
                register(name, city, bloodGroup, password, mobile)
            }
        }
    }

    private fun register(name: String, city: String, bloodGroup: String, password: String, mobile: String) {
        val stringRequest = object : StringRequest(Method.POST, Endpoints.register_url, Listener<String> { response ->
            if (response == "Success") {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
                    .putString("city", city).apply()
                Toast.makeText(this@RegisterActivity, response, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this@RegisterActivity, response, Toast.LENGTH_SHORT).show()
            }
        }, ErrorListener { error ->
            Toast.makeText(this@RegisterActivity, "Something went wrong:(", Toast.LENGTH_SHORT).show()
            Log.d("VOLLEY", error.message ?: "Error")
        }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = name
                params["city"] = city
                params["blood_group"] = bloodGroup
                params["password"] = password
                params["number"] = mobile
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }

    private fun isValid(name: String, city: String, bloodGroup: String, password: String, mobile: String): Boolean {
        val validBloodGroups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        return when {
            name.isEmpty() -> {
                showMessage("Name is empty")
                false
            }
            city.isEmpty() -> {
                showMessage("City name is required")
                false
            }
            !validBloodGroups.contains(bloodGroup) -> {
                showMessage("Blood group invalid, choose from $validBloodGroups")
                false
            }
            mobile.length != 10 -> {
                showMessage("Invalid mobile number, number should be of 10 digits")
                false
            }
            else -> true
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
