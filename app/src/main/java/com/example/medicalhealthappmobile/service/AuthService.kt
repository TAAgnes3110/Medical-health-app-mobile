package com.example.medicalhealthappmobile.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.MainActivity
import com.example.medicalhealthappmobile.domain.repository.AuthRepository

class AuthService(
    private val view: AppCompatActivity,
    private val authRepository: AuthRepository
) {
    fun checkUserStatus() {
        authRepository.checkUserStatus { isLoggedIn ->
            if (isLoggedIn) {
                view.startActivity(Intent(view, MainActivity::class.java))
                view.finish()
            }
        }
    }

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authRepository.login(email, password) { success, message ->
            if (success) {
                view.startActivity(Intent(view, MainActivity::class.java))
                view.finish()
            }
            callback(success, message)
        }
    }

    fun signup(fullname: String, password: String, email: String, mobile: String, dob: String, callback: (Boolean, String) -> Unit) {
        authRepository.signup(fullname, password, email, mobile, dob) { success, message ->
            if (success) {
                view.startActivity(Intent(view, MainActivity::class.java))
                view.finish()
            }
            callback(success, message)
        }
    }

    fun signOut(callback: (Boolean) -> Unit) {
        authRepository.signOut(callback)
    }
}