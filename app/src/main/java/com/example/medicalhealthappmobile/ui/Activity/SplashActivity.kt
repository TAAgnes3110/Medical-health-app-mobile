package com.example.medicalhealthappmobile.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Main).launch {
            // Simulate a delay for splash screen
            delay(2000) // 2 seconds
            // Navigate to MainActivity or any other activity
            startActivity(Intent(this@SplashActivity, WelcomeScreenActivity::class.java))

            finish() // Close SplashActivity
        }
    }
}