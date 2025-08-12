package com.example.medicalhealthappmobile.domain.repository

interface AuthRepository {
    fun checkUserStatus(callback: (Boolean) -> Unit)
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit)
    fun signup(fullname: String, password: String, email: String, mobile: String, dob: String, callback: (Boolean, String) -> Unit)
    fun sendOtp(email: String, callback: (Boolean, String) -> Unit)
    fun verifyOtp(email: String, otp: String, callback: (Boolean, String) -> Unit)
    fun resetPassword(email: String, newPassword: String, callback: (Boolean, String) -> Unit)
}