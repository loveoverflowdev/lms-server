package database.schemas.group

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.course.CourseTable
import models.products.group.CourseGroup
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

data class CourseGroupEntity(
    override val id: String,
    val title: String,
    val coverImage: String?,
    val primaryCoins: Int,
    val secondaryCoins: Int?,
    val description: String,
) : BaseEntity(id) {
    companion object {
        fun of(model: CourseGroup): CourseGroupEntity {
            return CourseGroupEntity(
                id = model.id,
                title = model.title,
                coverImage = model.coverImage,
                primaryCoins = model.primaryCoins,
                secondaryCoins = model.secondaryCoins,
                description = model.description,
            )
        }
    }

    override fun toModel(): CourseGroup {
        return CourseGroup(
            id = this.id,
            title = this.title,
            coverImage = this.coverImage,
            primaryCoins = this.primaryCoins,
            secondaryCoins = this.secondaryCoins,
            description = this.description
        )
    }
}

object CourseGroupTable: BaseTable("course_group") {
    val title = varchar("title", length = 255)
    val coverImage = varchar("cover_image", length = 255).nullable()
    val primaryCoins = integer("primary_coins")
    val secondaryCoins = integer("secondary_coins").nullable()
    val description = varchar("description", length = 255)

    override val primaryKey = PrimaryKey(id)
}

class CourseGroupSchema(
    database: Database
) : BaseSchema<CourseGroupTable, CourseGroupEntity>(database) {
    override suspend fun create(entity: CourseGroupEntity)
    : CourseGroupEntity = dbQuery {
        CourseGroupTable.insert {
            it[title] = entity.title
            it[coverImage] = entity.coverImage
            it[primaryCoins] = entity.primaryCoins
            it[secondaryCoins] = entity.secondaryCoins
            it[description] = entity.description
        }.run {
            CourseGroupEntity(
                id = this[CourseGroupTable.id].value,
                title = this[CourseGroupTable.title],
                coverImage = this[CourseGroupTable.coverImage],
                primaryCoins = this[CourseGroupTable.primaryCoins],
                secondaryCoins = this[CourseGroupTable.secondaryCoins],
                description = this[CourseGroupTable.description]
            )
        }
    }

    override suspend fun read(id: String)
    : CourseGroupEntity? = dbQuery {
        CourseGroupTable.select {
            CourseGroupTable.id.eq(id)
        }.map {
            CourseGroupEntity(
                id = it[CourseGroupTable.id].value,
                title = it[CourseGroupTable.title],
                coverImage = it[CourseGroupTable.coverImage],
                primaryCoins = it[CourseGroupTable.primaryCoins],
                secondaryCoins = it[CourseGroupTable.secondaryCoins],
                description = it[CourseGroupTable.description]
            )
        }.singleOrNull()
    }

    override suspend fun update(id: String, entity: CourseGroupEntity)
    : CourseGroupEntity? = dbQuery{
        CourseGroupTable.update({
            CourseGroupTable.id.eq(id)
        }) {
            it[title] = entity.title
            it[coverImage] = entity.coverImage
            it[primaryCoins] = entity.primaryCoins
            it[secondaryCoins] = entity.secondaryCoins
            it[description] = entity.description
        }.let { updateResult ->
            if (updateResult > 0) {
                entity.copy(id = id)
            } else {
                null
            }
        }
    }

    override suspend fun delete(id: String)
    : CourseGroupEntity? = dbQuery {
        read(id)?.let { entity ->
            CourseGroupTable.deleteWhere { CourseGroupTable.id.eq(id) }.let { deleteResult ->
                if (deleteResult > 0) {
                    entity
                } else {
                    null
                }
            }
        }
    }
}




