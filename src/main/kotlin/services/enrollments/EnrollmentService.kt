package services.enrollments

import commands.CustomerEnrollCourseCommand
import commands.ViewCourseEnrollmentCommand
import commands.ViewCourseEnrollmentListCommand
import models.enrollments.CourseEnrollment

class EnrollmentService: IEnrollmentService {
    override suspend fun enroll(command: CustomerEnrollCourseCommand): Result<CourseEnrollment> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseEnrollment(command: ViewCourseEnrollmentCommand): Result<CourseEnrollment> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseEnrollmentList(command: ViewCourseEnrollmentListCommand): Result<List<CourseEnrollment>> {
        TODO("Not yet implemented")
    }
}
