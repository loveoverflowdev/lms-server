package repositories.products.course

import models.products.course.Course
import repositories.base.BaseRepository

class CourseRepository: BaseRepository<Course>(), ICourseRepository {
    override suspend fun getAll(): Result<List<Course>> {
        return Result.success(mutableListOf(
            Course(
               id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0.0,
                secondaryCoins = 0.0
            ),
            Course(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0.0,
                secondaryCoins = 0.0
            ),
            Course(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0.0,
                secondaryCoins = 0.0
            ),
            Course(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0.0,
                secondaryCoins = 0.0
            ),
            Course(
                id = "sùydgsudjfsdjbsdjfbsdjfnsdf",
                title = "Learn Python like a Professional",
                coverImage = "https://i3.ytimg.com/vi/XKHEtdqhLK8/maxresdefault.jpg",
                instructor = "Corey Schafer",
                description = "Development",
                primaryCoins = 0.0,
                secondaryCoins = 0.0
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
            primaryCoins = 0.0,
            secondaryCoins = 0.0
        ))
    }

    override suspend fun find(predicate: (Course) -> Boolean): Result<List<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun add(entity: Course): Result<Course> {
        return Result.success(entity)
    }

    override suspend fun delete(id: String): Result<Course?> {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: Course): Result<Course?> {
        TODO("Not yet implemented")
    }
}