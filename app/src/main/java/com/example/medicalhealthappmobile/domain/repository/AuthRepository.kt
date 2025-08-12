package com.example.medicalhealthappmobile.domain.repository

interface AuthRepository {
    fun checkUserStatus(callback: (Boolean) -> Unit)
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit)
    fun signup(fullname: String, password: String, email: String, mobile: String, dob: String, callback: (Boolean, String) -> Unit)
    fun signOut(callback: (Boolean) -> Unit)
}