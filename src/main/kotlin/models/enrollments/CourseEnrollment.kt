package models.enrollments

import kotlinx.datetime.LocalDateTime
import models.enrollments.base.Enrollment

class CourseEnrollment(
    id: String,
    userId: String,
    enrollmentDateTime: LocalDateTime,
    expiredDateTime: LocalDateTime,
    val courseId: String
) : Enrollment(
    id = id,
    userId = userId,
    enrollmentDateTime = enrollmentDateTime,
    expiredDateTime = expiredDateTime,
)
