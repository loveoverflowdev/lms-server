package repositories.products.course

import database.DatabaseFactory
import database.schemas.course.CourseEntity
import database.schemas.course.CourseSchema
import models.products.course.Course
import java.sql.SQLException

class CourseRepository: ICourseRepository {

    private val courseSchema: CourseSchema = CourseSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun getAll(): Result<List<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): Result<Course?> {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (Course) -> Boolean): Result<List<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: Course): Result<Course> {
        return courseSchema.create(CourseEntity.of(model))
            .run {
                Result.success(toModel())
            }
    }

    override suspend fun delete(id: String): Result<Course> {
        return courseSchema
            .delete(id)
            ?.run {
                Result.success(toModel())
            }
            ?: Result.failure(SQLException("Error while delete course in database"))
    }

    override suspend fun update(id: String, model: Course): Result<Course> {
        return courseSchema
            .update(id, CourseEntity.of(model))
            ?.run {
                Result.success(toModel())
            }
            ?: Result.failure(SQLException("Error while update course in database"))
    }
}
