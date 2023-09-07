package database.schemas.membership

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.course.CourseTable
import database.schemas.group.CourseGroupTable
import models.base.Model
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

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
    override suspend fun create(entity: CourseGroupMembershipEntity)
    : CourseGroupMembershipEntity = dbQuery {
        CourseGroupMembershipTable.insert {
            it[courseId] = entity.courseId
            it[courseGroupId] = entity.courseGroupId
        }.run {
            CourseGroupMembershipEntity(
                id = this[CourseGroupMembershipTable.id].value,
                courseId = this[CourseGroupMembershipTable.courseId].value,
                courseGroupId = this[CourseGroupMembershipTable.courseGroupId].value,
            )
        }
    }

    override suspend fun read(id: String)
    : CourseGroupMembershipEntity? = dbQuery {
        CourseGroupMembershipTable.select {
            CourseGroupMembershipTable.id.eq(id)
        }.map {
            CourseGroupMembershipEntity(
                id = it[CourseGroupMembershipTable.id].value,
                courseId = it[CourseGroupMembershipTable.courseId].value,
                courseGroupId = it[CourseGroupMembershipTable.courseGroupId].value
            )
        }.singleOrNull()
    }

    override suspend fun delete(id: String)
    : CourseGroupMembershipEntity? = dbQuery {
        read(id)?.let { entity ->
            CourseGroupMembershipTable.deleteWhere { CourseGroupMembershipTable.id.eq(id) }.let { deleteResult ->
                if (deleteResult > 0) {
                    entity
                } else {
                    null
                }
            }
        }
    }

    override suspend fun update(id: String, entity: CourseGroupMembershipEntity)
    : CourseGroupMembershipEntity? = dbQuery {
        CourseGroupMembershipTable.update({
            CourseGroupMembershipTable.id.eq(id)
        }) {
            it[courseId] = entity.courseId
            it[courseGroupId] = entity.courseGroupId
        }.let { updateResult ->
            if (updateResult > 0) {
                entity.copy(id = id)
            } else {
                null
            }
        }
    }
}
