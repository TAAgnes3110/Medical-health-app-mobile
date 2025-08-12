package com.example.medicalhealthappmobile.domain.model

import java.time.LocalDateTime

data class Review(
    val id: String,
    val customerId: String,
    val doctorId: String,
    val rating: Double,
    val comment: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
