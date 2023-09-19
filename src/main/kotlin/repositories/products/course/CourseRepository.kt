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

    override suspend fun getAll(): List<Course> {
        return courseSchema.selectAll()
    }

    override suspend fun findById(id: String): Course? {
        return courseSchema.read(id)?.toModel()
    }

    override suspend fun find(predicate: (Course) -> Boolean): List<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: Course): Course {
        return courseSchema.create(CourseEntity.of(model)).toModel()
    }

    override suspend fun delete(id: String): Course? {
        return courseSchema.delete(id)?.toModel()
    }

    override suspend fun update(id: String, model: Course): Course? {
        return courseSchema.update(id, CourseEntity.of(model))?.toModel()
    }
}
