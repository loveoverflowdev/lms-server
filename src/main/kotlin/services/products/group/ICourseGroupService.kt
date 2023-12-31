package services.products.group

import commands.*
import models.products.group.CourseGroup

interface ICourseGroupService {
    suspend fun getCourseGroupListOnTop(command: GetCourseGroupListOnTopCommand)
    : Result<List<CourseGroup>>

    suspend fun getCourseGroupList(command: GetCourseListBySearchTextCommand)
    : Result<List<CourseGroup>>

    suspend fun getCourseGroup(command: GetCourseGroupByIdCommand)
    : Result<CourseGroup?>

    suspend fun createCourseGroup(command: CreateCourseGroupCommand) : Result<CourseGroup>

    suspend fun updateCourseGroup(command: UpdateCourseGroupCommand) : Result<CourseGroup>

    suspend fun deleteCourseGroup(command: DeleteCourseGroupCommand) : Result<CourseGroup>

    suspend fun addCourseToGroup(command: AddCourseToGroupCommand)
}
