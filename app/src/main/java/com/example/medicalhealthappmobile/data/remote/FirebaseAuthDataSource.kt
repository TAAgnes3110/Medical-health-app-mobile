package com.example.medicalhealthappmobile.data.remote

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthDataSource {
    private val auth = FirebaseAuth.getInstance()

    fun checkUserStatus(callback: (Boolean) -> Unit) {
        val currentUser = auth.currentUser
        callback(currentUser != null)
    }

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Email and password cannot be empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, "Sign in successful")
            } else {
                callback(false, task.exception?.message ?: "Sign in failed")
            }
        }
    }

    fun signup(fullname: String, password: String, email: String, mobile: String, dob: String, callback: (Boolean, String) -> Unit) {
        if (fullname.isEmpty() || password.isEmpty() || email.isEmpty() || mobile.isEmpty() || dob.isEmpty()) {
            callback(false, "All fields must be filled")
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, "Sign up successful")
            } else {
                callback(false, task.exception?.message ?: "Sign up failed")
            }
        }
    }

    fun sendOtp(email: String, callback: (Boolean, String) -> Unit) {
        if (email.isEmpty()) {
            callback(false, "Email cannot be empty")
            return
        }
        // Giả lập gửi OTP
        callback(true, "OTP sent to $email")
    }

    fun verifyOtp(email: String, otp: String, callback: (Boolean, String) -> Unit) {
        if (otp.isEmpty()) {
            callback(false, "OTP cannot be empty")
            return
        }
        // Giả lập xác minh OTP (OTP mặc định là "123456")
        if (otp == "123456") {
            callback(true, "OTP verified")
        } else {
            callback(false, "Invalid OTP")
        }
    }

    fun resetPassword(email: String, newPassword: String, callback: (Boolean, String) -> Unit) {
        if (email.isEmpty() || newPassword.isEmpty()) {
            callback(false, "Email and new password cannot be empty")
            return
        }
        // Giả lập đặt lại mật khẩu
        callback(true, "Password reset successful")
    }
}