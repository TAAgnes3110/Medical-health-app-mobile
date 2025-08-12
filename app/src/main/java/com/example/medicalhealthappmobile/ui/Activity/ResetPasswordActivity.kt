package com.example.medicalhealthappmobile.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.R
import com.example.medicalhealthappmobile.ui.fragment.VerifyEmail

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reset_password)

        if (savedInstanceState == null) {
            val email = intent.getStringExtra("EMAIL") ?: ""
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, VerifyEmail.newInstance(email))
                .commit()
        }
    }
}