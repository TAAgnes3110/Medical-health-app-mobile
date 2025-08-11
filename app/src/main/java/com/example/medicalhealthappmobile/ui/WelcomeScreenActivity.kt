package com.example.medicalhealthappmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.R

class WelcomeScreenActivity : AppCompatActivity() {
    private lateinit var logIn : Button
    private lateinit var signUp : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen)
        initUI()
        logIn.setOnClickListener {
            val intent = Intent(this@WelcomeScreenActivity, LogInActivity::class.java)
            intent.putExtra("FORCE_LOGIN", true)
            startActivity(intent)
            finish()
        }

        signUp.setOnClickListener {
            startActivity(Intent(this@WelcomeScreenActivity, SignUpActivity::class.java))
            finish()
        }
    }

    fun initUI(){
        logIn = findViewById(R.id.logIn)
        signUp = findViewById(R.id.signUp)
    }
}