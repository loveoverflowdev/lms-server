package repositories.products.course

import models.products.course.Course

interface ICourseRepository {
     suspend fun getAll(): List<Course>
     suspend fun findById(id: String): Course?
     suspend fun find(predicate: (Course) -> Boolean): List<Course>
     suspend fun create(model: Course): Course
     suspend fun delete(id: String): Course?
     suspend fun update(id: String, model: Course): Course?
}
