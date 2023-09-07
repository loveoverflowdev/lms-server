package repositories.products.group

import database.DatabaseFactory
import database.schemas.group.CourseGroupEntity
import database.schemas.group.CourseGroupSchema
import models.products.group.CourseGroup
import repositories.base.BaseRepository
import java.sql.SQLException

class CourseGroupRepository: BaseRepository<CourseGroup>(), ICourseGroupRepository {

    private val courseGroupSchema: CourseGroupSchema = CourseGroupSchema(
        database = DatabaseFactory.databaseShared
    )

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

    override suspend fun add(model: CourseGroup): Result<CourseGroup> {
        return courseGroupSchema.create(CourseGroupEntity.of(model))
            .run {
                Result.success(toModel())
            }
    }

    override suspend fun delete(id: String): Result<CourseGroup> {
        TODO("Not yet implemented")
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
