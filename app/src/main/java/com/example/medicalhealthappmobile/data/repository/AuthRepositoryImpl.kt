package com.example.medicalhealthappmobile.data.repository

import com.example.medicalhealthappmobile.data.remote.FirebaseAuthDataSource
import com.example.medicalhealthappmobile.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authDataSource: FirebaseAuthDataSource) : AuthRepository {
    override fun checkUserStatus(callback: (Boolean) -> Unit) {
        authDataSource.checkUserStatus(callback)
    }

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authDataSource.login(email, password, callback)
    }

    override fun signup(fullname: String, password: String, email: String, mobile: String, dob: String, callback: (Boolean, String) -> Unit) {
        authDataSource.signup(fullname, password, email, mobile, dob, callback)
    }

    override fun sendOtp(email: String, callback: (Boolean, String) -> Unit) {
        authDataSource.sendOtp(email, callback)
    }

    override fun verifyOtp(email: String, otp: String, callback: (Boolean, String) -> Unit) {
        authDataSource.verifyOtp(email, otp, callback)
    }

    override fun resetPassword(email: String, newPassword: String, callback: (Boolean, String) -> Unit) {
        authDataSource.resetPassword(email, newPassword, callback)
    }
}