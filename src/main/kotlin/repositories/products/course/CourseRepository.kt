package repositories.products.course

import database.DatabaseFactory
import database.schemas.course.CourseEntity
import database.schemas.course.CourseSchema
import models.products.course.Course
import repositories.base.BaseRepository
import java.sql.SQLException

class CourseRepository: BaseRepository<Course>(), ICourseRepository {

    private val courseSchema: CourseSchema = CourseSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun getAll(): Result<List<Course>> {
        return Result.success(mutableListOf(
            Course(
               id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            ),
            Course(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            ),
            Course(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0,
                secondaryCoins = 0
            ),
        ))
    }

    override suspend fun findById(id: String): Result<Course?> {
        return Result.success(Course(
            id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
            title = "Learn Python like a Professional",
            coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
            instructor = "Corey Schafer",
            description = "Development",
            primaryCoins = 0,
            secondaryCoins = 0
        ))
    }

    override suspend fun find(predicate: (Course) -> Boolean): Result<List<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun add(model: Course): Result<Course> {
        return courseSchema.create(CourseEntity.of(model))
            .run {
                Result.success(toModel())
            }
    }

    override suspend fun delete(id: String): Result<Course> {
        return courseSchema
            .delete(id)
            ?.run {
                Result.success(toModel())
            }
            ?: Result.failure(SQLException("Error while delete course in database"))
    }

    override suspend fun update(id: String, model: Course): Result<Course> {
        return courseSchema
            .update(id, CourseEntity.of(model))
            ?.run {
                Result.success(toModel())
            }
            ?: Result.failure(SQLException("Error while update course in database"))
    }
}
