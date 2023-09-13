package repositories.products.group

import database.DatabaseFactory
import database.schemas.group.CourseGroupEntity
import database.schemas.group.CourseGroupSchema
import database.schemas.membership.CourseGroupMembershipEntity
import database.schemas.membership.CourseGroupMembershipSchema
import models.products.group.CourseGroup
import java.sql.SQLException

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

    override  suspend fun removeCourseFromGroup(courseId: String, courseGroupId: String) {
        courseGroupMembershipSchema.delete(
            courseId = courseId,
            courseGroupId = courseGroupId,
        )
    }

    override suspend fun getAll(): Result<List<CourseGroup>> {
        return Result.success(mutableListOf(
            CourseGroup(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            ),
            CourseGroup(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            ),
            CourseGroup(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            ),
        ))
    }

    override suspend fun findById(id: String): Result<CourseGroup?> {
        return Result.success(
            CourseGroup(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            )
        )
    }

    override suspend fun find(predicate: (CourseGroup) -> Boolean): Result<List<CourseGroup>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: CourseGroup): Result<CourseGroup> {
        return courseGroupSchema.create(CourseGroupEntity.of(model))
            .run {
                Result.success(toModel())
            }
    }

    override suspend fun delete(id: String): Result<CourseGroup> {
        return courseGroupSchema.delete(id)
            ?.run {
                Result.success(toModel())
            }
            ?: Result.failure(SQLException("Error while update course in database"))
    }

    override suspend fun update(id: String, model: CourseGroup): Result<CourseGroup> {
        return courseGroupSchema
            .update(id, CourseGroupEntity.of(model))
            ?.run {
                Result.success(toModel())
            }
            ?: Result.failure(SQLException("Error while update course in database"))
    }
}
