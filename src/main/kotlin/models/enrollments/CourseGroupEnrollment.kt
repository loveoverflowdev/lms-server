package models.enrollments

import kotlinx.datetime.LocalDateTime
import models.enrollments.base.Enrollment

class CourseGroupEnrollment(
    id: String,
    userId: String,
    enrollmentDateTime: LocalDateTime,
    expiredDateTime: LocalDateTime,

    val courseGroupId: String
) : Enrollment(
    id = id,
    userId = userId,
    enrollmentDateTime = enrollmentDateTime,
    expiredDateTime = expiredDateTime,
)
