package database.schemas.enrollments

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.course.CourseTable
import database.schemas.user.UserTable
import kotlinx.datetime.LocalDateTime
import models.base.Model
import models.enrollments.CourseEnrollment
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.javatime.datetime

data class CourseEnrollmentEntity(
    override val id: String,
    val userId: String,
    val courseId: String,
    val enrollmentDateTime: LocalDateTime,
    val expirationDateTime: LocalDateTime,
) : BaseEntity(id) {
    companion object {
        fun of(model: CourseEnrollment): CourseEnrollmentEntity {
            return CourseEnrollmentEntity(
                id = model.id,
                userId = model.userId,
                courseId = model.courseId,
                enrollmentDateTime = model.enrollmentDateTime,
                expirationDateTime = model.expirationDateTime,
            )
        }
    }

    override fun toModel(): Model {
        return CourseEnrollment(
            id = this.id,
            userId = this.userId,
            courseId = this.courseId,
            enrollmentDateTime = enrollmentDateTime,
            expirationDateTime = expirationDateTime,
        )
    }
}

object CourseEnrollmentTable: BaseTable("course_enrollment") {
    val userId = reference("user_id", UserTable.id)
    val courseId = reference("course_id", CourseTable.id)
    val enrollmentDateTime = datetime("enrollment_date_time")
    val expirationDateTime = datetime("expiration_date_time")

    override val primaryKey = PrimaryKey(id)
}

class CourseEnrollmentSchema(
    database: Database
) : BaseSchema<CourseEnrollmentTable, CourseEnrollmentEntity>(database) {
    override suspend fun create(entity: CourseEnrollmentEntity): CourseEnrollmentEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun read(id: String): CourseEnrollmentEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CourseEnrollmentEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: CourseEnrollmentEntity): CourseEnrollmentEntity? {
        TODO("Not yet implemented")
    }
}
