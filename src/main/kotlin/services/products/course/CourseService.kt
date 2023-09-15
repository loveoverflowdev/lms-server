package services.products.course

import commands.*
import database.schemas.course.CourseSchema
import models.products.course.Course
import repositories.products.course.ICourseRepository

class CourseService(
    private val courseRepository: ICourseRepository
) : ICourseService {

    override suspend fun getCourseListOnTop(command: GetCourseListOnTopCommand): Result<List<Course>> {
        return Result.success(courseRepository.getAll())
    }

    override suspend fun getCourseListBySearchText(command: GetCourseListBySearchTextCommand): Result<List<Course>> {
        return Result.success(courseRepository.getAll())
    }

    override suspend fun getCourseById(command: GetCourseByIdCommand): Result<Course?> {
        return courseRepository.findById(command.id ?: "").run {
            Result.success(this)
        }
    }

    override suspend fun createCourse(command: CreateCourseCommand): Result<Course> {
        return courseRepository.create(command.toModel()).run {
            Result.success(this)
        }
    }

    override suspend fun updateCourse(command: UpdateCourseCommand): Result<Course> {
        return courseRepository.update(command.id, command.toModel())?.run {
            Result.success(this)
        } ?: Result.failure(Exception("Cannot update course"))
    }

    override suspend fun deleteCourse(command: DeleteCourseCommand): Result<Course> {
        return courseRepository.delete(command.id)?.run {
            Result.success(this)
        } ?: Result.failure(Exception("Cannot delete course"))
    }
}
