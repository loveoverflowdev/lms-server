package repositories.products.group

import database.schemas.membership.CourseGroupMembershipEntity
import models.products.group.CourseGroup
import org.jetbrains.annotations.Nullable
import repositories.base.IEntityRepository

interface ICourseGroupRepository: IEntityRepository<CourseGroup> {
    suspend fun addCourseToGroup(courseId: String, courseGroupId: String)

    suspend fun removeCourseFromGroup(courseId: String, courseGroupId: String)
}
