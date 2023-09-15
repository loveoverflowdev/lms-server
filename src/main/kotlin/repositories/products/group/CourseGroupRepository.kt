package repositories.products.group

import database.DatabaseFactory
import database.schemas.group.CourseGroupEntity
import database.schemas.group.CourseGroupSchema
import database.schemas.membership.CourseGroupMembershipEntity
import database.schemas.membership.CourseGroupMembershipSchema
import models.products.group.CourseGroup

class CourseGroupRepository: ICourseGroupRepository {

    private val courseGroupSchema: CourseGroupSchema = CourseGroupSchema(
        database = DatabaseFactory.databaseShared
    )

    private val courseGroupMembershipSchema: CourseGroupMembershipSchema = CourseGroupMembershipSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun addCourseToGroup(courseId: String, courseGroupId: String) {
        courseGroupMembershipSchema.create(CourseGroupMembershipEntity(
            id = "",
            courseId = courseId,
            courseGroupId = courseGroupId
        ))
    }

    override suspend fun removeCourseFromGroup(courseId: String, courseGroupId: String) {
        courseGroupMembershipSchema.delete(
            courseId = courseId,
            courseGroupId = courseGroupId,
        )
    }

    override suspend fun getAll(): List<CourseGroup> {
        return courseGroupSchema.selectAll()
    }

    override suspend fun findById(id: String): CourseGroup? {
        TODO()
    }

    override suspend fun create(model: CourseGroup): CourseGroup {
        return courseGroupSchema.create(CourseGroupEntity.of(model)).toModel()
    }

    override suspend fun delete(id: String): CourseGroup? {
        return courseGroupSchema.delete(id)?.toModel()
    }

    override suspend fun update(id: String, model: CourseGroup): CourseGroup? {
        return courseGroupSchema
            .update(id, CourseGroupEntity.of(model))
            ?.toModel()
    }
}
