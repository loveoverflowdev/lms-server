package database.schemas.membership

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.course.CourseTable
import database.schemas.group.CourseGroupEntity
import database.schemas.group.CourseGroupTable
import models.base.Model
import org.jetbrains.exposed.sql.Database

data class CourseGroupMembershipEntity(
    override val id: String,
    val courseId: String,
    val courseGroupId: String
) : BaseEntity(id) {
    override fun toModel(): Model {
        TODO("Not yet implemented")
    }
}

object CourseGroupMembershipTable: BaseTable("course_group_membership") {
    val courseId = reference("course_id", CourseTable.id)
    val courseGroupId = reference("course_group_id", CourseGroupTable.id)

    override val primaryKey = PrimaryKey(id)
}

class CourseGroupMembershipSchema(
    database: Database
) : BaseSchema<CourseGroupMembershipTable, CourseGroupMembershipEntity>(database) {
    override suspend fun create(entity: CourseGroupMembershipEntity): CourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun read(id: String): CourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: CourseGroupMembershipEntity): CourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }

}
