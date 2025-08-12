package com.example.medicalhealthappmobile.ui.activity

import android.content.Intent
import android.os.Bundle
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
import com.example.medicalhealthappmobile.ui.activity.SignUpActivity
import com.example.medicalhealthappmobile.ui.activity.ResetPasswordActivity
import com.example.medicalhealthappmobile.ui.activity.WelcomeScreenActivity
import com.google.android.material.button.MaterialButton

class LogInActivity : AppCompatActivity() {
    private lateinit var back: ImageButton
    private lateinit var logIn: MaterialButton
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUp: TextView
    private lateinit var forgotPassword: TextView
    private lateinit var google: ImageButton
    private lateinit var facebook: ImageButton
    private lateinit var vantay: ImageButton
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)
        initUI()
        authService = AuthService(this, AuthRepositoryImpl(FirebaseAuthDataSource()))

        val forceLogin = intent.getBooleanExtra("FORCE_LOGIN", false)
        if (!forceLogin) {
            authService.checkUserStatus()
        }

        back.setOnClickListener {
            startActivity(Intent(this, WelcomeScreenActivity::class.java))
            finish()
        }

        logIn.setOnClickListener {
            val emailInput = email.text.toString()
            val passwordInput = password.text.toString()
            authService.login(emailInput, passwordInput) { success, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

        signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            intent.putExtra("EMAIL", email.text.toString().trim())
            startActivity(intent)
        }
    }

    private fun initUI() {
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