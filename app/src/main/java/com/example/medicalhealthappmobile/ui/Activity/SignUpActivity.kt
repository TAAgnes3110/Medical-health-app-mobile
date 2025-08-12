package com.example.medicalhealthappmobile.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.R
import com.example.medicalhealthappmobile.data.remote.FirebaseAuthDataSource
import com.example.medicalhealthappmobile.data.repository.AuthRepositoryImpl
import com.example.medicalhealthappmobile.service.AuthService

class SignUpActivity : AppCompatActivity() {
    private lateinit var back: ImageButton
    private lateinit var signUp: Button
    private lateinit var fullnameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var mobileInput: EditText
    private lateinit var dobInput: EditText
    private lateinit var google: ImageButton
    private lateinit var facebook: ImageButton
    private lateinit var vantay: ImageButton
    private lateinit var logIn: TextView
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        initUI()
        authService = AuthService(this, AuthRepositoryImpl(FirebaseAuthDataSource()))

        back.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            intent.putExtra("FORCE_LOGIN", true)
            startActivity(intent)
            finish()
        }

        signUp.setOnClickListener {
            val fullnameIn = fullnameInput.text.toString()
            val passwordIn = passwordInput.text.toString()
            val emailIn = emailInput.text.toString()
            val mobileIn = mobileInput.text.toString()
            val dobIn = dobInput.text.toString()

            authService.signup(fullnameIn, passwordIn, emailIn, mobileIn, dobIn) { success, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

        logIn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            intent.putExtra("FORCE_LOGIN", true)
            startActivity(intent)
            finish()
        }
    }

    private fun initUI() {
        back = findViewById(R.id.back_button)
        signUp = findViewById(R.id.sign_up_button)
        fullnameInput = findViewById(R.id.fullname_input)
        passwordInput = findViewById(R.id.password_input)
        emailInput = findViewById(R.id.email_input)
        mobileInput = findViewById(R.id.mobile_input)
        dobInput = findViewById(R.id.dob_input)
        google = findViewById(R.id.gglogin)
        facebook = findViewById(R.id.fblogin)
        vantay = findViewById(R.id.vantaylogin)
        logIn = findViewById(R.id.logIn)
    }
}