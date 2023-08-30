package services.products.course

import commands.*
import models.products.course.Course

interface ICourseService {
    suspend fun getCourseListOnTop(command : GetCourseListOnTopCommand) : Result<List<Course>>
    suspend fun getCourseListBySearchText(command : GetCourseListBySearchTextCommand) : Result<List<Course>>
    suspend fun getCourseById(command : GetCourseByIdCommand) : Result<Course?>
    suspend fun createCourse(command : CreateCourseCommand) : Result<Course>
    suspend fun updateCourse(command : UpdateCourseCommand) : Result<Course?>
    suspend fun deleteCourse(command : DeleteCourseCommand) : Result<Course?>
}
