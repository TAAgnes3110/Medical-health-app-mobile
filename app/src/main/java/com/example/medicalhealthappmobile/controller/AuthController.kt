package com.example.medicalhealthappmobile.controller
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalhealthappmobile.MainActivity
import com.google.firebase.auth.FirebaseAuth

class AuthController (private val view: AppCompatActivity) {
    private val auth = FirebaseAuth.getInstance()

    fun checkUserStatus(callnback: (Boolean) -> Unit){
        val currentUser = auth.currentUser
        callnback(currentUser != null)
    }

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Email and password cannot be empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task -> if (task.isSuccessful){
                callback(true, "Sign in successful")
                view.startActivity(Intent(view, MainActivity::class.java))
                view.finish()
            }
            else {
                callback(false, task.exception?.message ?: "Sign in failed")
            }
        }
    }


}