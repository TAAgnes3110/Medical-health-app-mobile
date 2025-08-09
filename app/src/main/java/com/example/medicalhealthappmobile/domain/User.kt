package com.example.medicalhealthappmobile.domain

open class User(
    open val id: String,
    open var fullname: String,
    open var email: String,
    open var password: String,
    open var phone: String,
    open var dateOfBirth: String
)
