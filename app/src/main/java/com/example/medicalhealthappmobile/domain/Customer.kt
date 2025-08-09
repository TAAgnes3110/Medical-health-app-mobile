package com.example.medicalhealthappmobile.domain

data class Customer(
    override val id: String,
    override var fullname: String,
    override var email: String,
    override var password: String,
    override var phone: String,
    override var dateOfBirth: String,
    var favorites: MutableList<String> = mutableListOf() // list of doctorIds
) : User(id, fullname, email, password, phone, dateOfBirth)
