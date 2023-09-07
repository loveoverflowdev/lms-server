package services.products.group

import commands.*
import models.products.group.CourseGroup
import org.jetbrains.annotations.Nullable

interface ICourseGroupService {
    suspend fun getCourseGroupListOnTop(command: GetCourseGroupListOnTopCommand)
    : Result<List<CourseGroup>>

    suspend fun getCourseGroupListBySearchTextCommand(command: GetCourseListBySearchTextCommand)
    : Result<List<CourseGroup>>

    suspend fun getCourseById(command: GetCourseByIdCommand)
    : Result<CourseGroup>

    suspend fun createCourseGroup(command: CreateCourseCommand) : Result<CourseGroup>

    suspend fun updateCourseGroup(command: UpdateCourseGroupCommand) : Result<CourseGroup>

    suspend fun deleteCourseGroup(command: DeleteCourseGroupCommand) : Result<CourseGroup>

    suspend fun addCourseToGroup(command: AddCourseToGroupCommand)
}
