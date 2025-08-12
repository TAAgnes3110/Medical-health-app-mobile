package com.example.medicalhealthappmobile.domain.model

import java.time.LocalDateTime

data class Appointment(
    val id: String,
    val customerId: String,
    val doctorId: String,
    val dateTime: LocalDateTime,
    var status: AppointmentStatus = AppointmentStatus.PENDING,
    var note: String? = null
)

enum class AppointmentStatus {
    PENDING, CONFIRMED, CANCELLED, COMPLETED
}
