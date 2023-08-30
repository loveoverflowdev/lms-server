package models.enrollments.base

import kotlinx.datetime.LocalDateTime

abstract class Enrollment(
    val id: String,
    val userId: String,
    val enrollmentDateTime: LocalDateTime,
    val expiredDateTime: LocalDateTime
)
