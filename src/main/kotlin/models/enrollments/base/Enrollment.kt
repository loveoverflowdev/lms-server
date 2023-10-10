package models.enrollments.base

import kotlinx.datetime.LocalDateTime
import models.base.Model

abstract class Enrollment(
    id: String,
    val userId: String,
    val enrollmentDateTime: LocalDateTime,
    val expirationDateTime: LocalDateTime
): Model(id = id)
