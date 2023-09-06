package services.products.course

import commands.*
import database.DatabaseFactory
import database.schemas.products.course.CourseSchema
import models.products.course.Course
import repositories.products.course.ICourseRepository

class CourseService(
    private val courseRepository: ICourseRepository
) : ICourseService {

    override suspend fun getCourseListOnTop(command: GetCourseListOnTopCommand): Result<List<Course>> {
        return courseRepository.getAll()
    }

    override suspend fun getCourseListBySearchText(command: GetCourseListBySearchTextCommand): Result<List<Course>> {
        return courseRepository.getAll()
    }

    override suspend fun getCourseById(command: GetCourseByIdCommand): Result<Course?> {
        return courseRepository.findById(command.id ?: "")
    }

    override suspend fun createCourse(command: CreateCourseCommand): Result<Course> {
        return courseRepository.add(command.toModel())
    }

    override suspend fun updateCourse(command: UpdateCourseCommand): Result<Course> {
        return courseRepository.update(command.toModel())
    }

    override suspend fun deleteCourse(command: DeleteCourseCommand): Result<Course> {
        return courseRepository.delete(command.id)
    }
}
