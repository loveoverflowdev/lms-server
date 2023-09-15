package repositories.products.group

import models.products.group.CourseGroup

interface ICourseGroupRepository {
    suspend fun addCourseToGroup(courseId: String, courseGroupId: String)
    suspend fun removeCourseFromGroup(courseId: String, courseGroupId: String)
    suspend fun getAll(): List<CourseGroup>
    suspend fun findById(id: String): CourseGroup?
    suspend fun create(model: CourseGroup): CourseGroup?
    suspend fun delete(id: String): CourseGroup?
    suspend fun update(id: String, model: CourseGroup): CourseGroup?
}
