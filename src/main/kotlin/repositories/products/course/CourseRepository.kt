package repositories.products.course

import database.DatabaseFactory
import database.schemas.course.CourseEntity
import database.schemas.course.CourseSchema
import models.products.course.Course

class CourseRepository: ICourseRepository {

    private val courseSchema: CourseSchema = CourseSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun getAll(): List<Course> {
        return courseSchema.selectAll()
    }

    override suspend fun find(id: String): Course? {
        return courseSchema.findCourse(id)
    }

    override suspend fun find(predicate: (Course) -> Boolean): List<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: Course): Course {
        return courseSchema.createCourse(model)
    }

    override suspend fun delete(id: String): Course? {
        return courseSchema.deleteCourse(id)
    }

    override suspend fun update(id: String, model: Course): Course? {
        return courseSchema.updateCourse(id, model)
    }
}
