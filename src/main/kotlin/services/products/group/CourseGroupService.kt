package services.products.group

import commands.*
import models.products.group.CourseGroup
import repositories.products.group.CourseGroupRepository

class CourseGroupService(
    private val courseGroupRepository: CourseGroupRepository
) : ICourseGroupService {
    override suspend fun getCourseGroupListOnTop(command: GetCourseListOnTopCommand): Result<List<CourseGroup>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseGroupListBySearchTextCommand(command: GetCourseListBySearchTextCommand): Result<List<CourseGroup>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseById(command: GetCourseByIdCommand): Result<CourseGroup> {
        TODO("Not yet implemented")
    }

    override suspend fun createCourseGroup(command: CreateCourseCommand): Result<CourseGroup> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourseGroup(command: UpdateCourseGroupCommand): Result<CourseGroup> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourseGroup(command: DeleteCourseGroupCommand): Result<CourseGroup> {
        TODO("Not yet implemented")
    }

    override suspend fun addCourseToGroup(command: AddCourseToGroupCommand) {
        courseGroupRepository.addCourseToGroup(
            courseId = command.courseId,
            courseGroupId = command.courseGroupId,
        )
    }
}
