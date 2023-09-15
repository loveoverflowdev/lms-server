package repositories.products.course

import models.products.course.Course

interface ICourseRepository {
     suspend fun getAll(): Result<List<Course>>
     suspend fun findById(id: String): Result<Course?>
     suspend fun find(predicate: (Course) -> Boolean): Result<List<Course>>
     suspend fun create(model: Course): Result<Course>
     suspend fun delete(id: String): Result<Course>
     suspend fun update(id: String, model: Course): Result<Course>
}
