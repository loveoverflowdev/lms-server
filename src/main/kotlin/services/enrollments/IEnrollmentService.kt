package services.enrollments

import commands.*
import models.enrollments.CourseEnrollment

interface IEnrollmentService {
    suspend fun enroll(command: CustomerEnrollCourseCommand): Result<CourseEnrollment>

    suspend fun getCourseEnrollment(command: ViewCourseEnrollmentCommand): Result<CourseEnrollment>

    suspend fun getCourseEnrollmentList(command: ViewCourseEnrollmentListCommand): Result<List<CourseEnrollment>>
}
