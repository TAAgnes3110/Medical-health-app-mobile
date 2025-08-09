package com.example.medicalhealthappmobile.domain

import java.time.LocalDateTime

data class TimeSlot(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val isAvailable: Boolean = true
)
