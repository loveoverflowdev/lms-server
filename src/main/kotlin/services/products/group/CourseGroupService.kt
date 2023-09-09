package services.products.group

import commands.*
import models.products.group.CourseGroup
import repositories.products.group.ICourseGroupRepository

class CourseGroupService(
    private val courseGroupRepository: ICourseGroupRepository
) : ICourseGroupService {
    override suspend fun getCourseGroupListOnTop(command: GetCourseGroupListOnTopCommand): Result<List<CourseGroup>> {
        return courseGroupRepository.getAll()
    }

    override suspend fun getCourseGroupListBySearchTextCommand(command: GetCourseListBySearchTextCommand): Result<List<CourseGroup>> {
        return courseGroupRepository.getAll()
    }

    override suspend fun getCourseGroupById(command: GetCourseGroupByIdCommand): Result<CourseGroup?> {
        return courseGroupRepository.findById(command.id ?: "")
    }

    override suspend fun createCourseGroup(command: CreateCourseGroupCommand): Result<CourseGroup> {
        return courseGroupRepository.add(command.toModel())
    }

    override suspend fun updateCourseGroup(command: UpdateCourseGroupCommand): Result<CourseGroup> {
        return courseGroupRepository.update(command.id, command.toModel())
    }

    override suspend fun deleteCourseGroup(command: DeleteCourseGroupCommand): Result<CourseGroup> {
        return courseGroupRepository.delete(
            id = command.id ?: "",
        )
    }

    override suspend fun addCourseToGroup(command: AddCourseToGroupCommand) {
        courseGroupRepository.addCourseToGroup(
            courseId = command.id ?: "",
            courseGroupId = command.courseGroupId ?: "",
        )
    }
}
