package com.example.medicalhealthappmobile.domain

data class Doctor(
    override val id: String,
    override var fullname: String,
    override var email: String,
    override var password: String,
    override var phone: String,
    override var dateOfBirth: String,
    var gender: String,
    var specialization: String,
    var yearsOfExperience: Int,
    var profile: String,
    var careerPath: String,
    var highlights: String,
    var schedule: MutableList<TimeSlot> = mutableListOf(),
    var reviews: MutableList<Review> = mutableListOf()
) : User(id, fullname, email, password, phone, dateOfBirth)
