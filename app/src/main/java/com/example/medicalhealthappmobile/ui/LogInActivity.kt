package com.example.medicalhealthappmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.MainActivity
import com.example.medicalhealthappmobile.R
import com.example.medicalhealthappmobile.controller.AuthController
import com.google.android.material.button.MaterialButton

class LogInActivity : AppCompatActivity() {
    private lateinit var back : ImageButton;
    private lateinit var logIn : MaterialButton;
    private lateinit var email : EditText;
    private lateinit var password : EditText;
    private lateinit var signUp : TextView;
    private lateinit var forgotPassword : TextView;
    private lateinit var google : ImageButton;
    private lateinit var facebook : ImageButton;
    private lateinit var vantay : ImageButton;
    private lateinit var authController: AuthController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)
        initUI();
        authController = AuthController(this)

        authController.checkUserStatus { isLoggedIn ->
            if(isLoggedIn){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        back.setOnClickListener {
            startActivity(Intent(this, WelcomeScreenActivity::class.java))
            finish()
        }

        logIn.setOnClickListener {
            val emailInput = email.text.toString()
            val passwordInput = password.text.toString()
            authController.login(emailInput, passwordInput) { success, message ->
                if (message != null) Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    public fun initUI(){
        back = findViewById(R.id.back_button)
        logIn = findViewById(R.id.login_button)
        email = findViewById(R.id.email_input)
        password = findViewById(R.id.password_input)
        signUp = findViewById(R.id.or_sign_up)
        forgotPassword = findViewById(R.id.forget_password)
        google = findViewById(R.id.gglogin)
        facebook = findViewById(R.id.fblogin)
        vantay = findViewById(R.id.vantaylogin)
    }
}