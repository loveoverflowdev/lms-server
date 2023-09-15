package database.schemas.course

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.group.CourseGroupTable
import models.products.course.Course
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

data class CourseEntity(
    override val id: String,
    val title: String,
    val coverImage: String?,
    val primaryCoins: Int,
    val secondaryCoins: Int?,
    val description: String,
    val instructor: String,
) : BaseEntity(id) {
    companion object {
        fun of(model: Course): CourseEntity {
            return CourseEntity(
                id = model.id,
                title = model.title,
                coverImage = model.coverImage,
                instructor = model.instructor,
                primaryCoins = model.primaryCoins,
                secondaryCoins = model.secondaryCoins,
                description = model.description
            )
        }
    }

    override fun toModel(): Course {
        return Course(
            id = this.id,
            title = this.title,
            coverImage = this.coverImage,
            instructor = this.instructor,
            primaryCoins = this.primaryCoins,
            secondaryCoins = this.secondaryCoins,
            description = this.description
        )
    }
}

object CourseTable: BaseTable("course") {
    val title = varchar("title", length = 255)
    val coverImage = varchar("cover_image", length = 255).nullable()
    val primaryCoins = integer("primary_coins")
    val secondaryCoins = integer("secondary_coins").nullable()
    val description = varchar("description", length = 255)
    val instructor = varchar("instructor", length = 255)

    override val primaryKey = PrimaryKey(id)
}

class CourseSchema(
    database: Database
) : BaseSchema<CourseTable, CourseEntity>(database) {
    suspend fun selectAll(): List<Course> = dbQuery {
        CourseGroupTable
            .selectAll()
            .map {
                Course(
                    id = it[CourseTable.id].value,
                    title = it[CourseTable.title],
                    coverImage = it[CourseTable.coverImage],
                    primaryCoins = it[CourseTable.primaryCoins],
                    secondaryCoins = it[CourseTable.secondaryCoins],
                    description = it[CourseTable.description],
                    instructor = it[CourseTable.instructor],
                )
            }
    }

    override suspend fun create(entity: CourseEntity)
    : CourseEntity = dbQuery {
         CourseTable.insert {
            it[title] = entity.title
            it[coverImage] = entity.coverImage
            it[primaryCoins] = entity.primaryCoins
            it[secondaryCoins] = entity.secondaryCoins
            it[description] = entity.description
            it[instructor] = entity.instructor
        }.run {
             CourseEntity(
                 id = this[CourseTable.id].value,
                 title = this[CourseTable.title],
                 coverImage = this[CourseTable.coverImage],
                 primaryCoins = this[CourseTable.primaryCoins],
                 secondaryCoins = this[CourseTable.secondaryCoins],
                 description = this[CourseTable.description],
                 instructor = this[CourseTable.instructor]
             )
        }
    }

    override suspend fun read(id: String)
    : CourseEntity? = dbQuery {
        CourseTable.select {
            CourseTable.id.eq(id)
        }.map {
            CourseEntity(
                id = it[CourseTable.id].value,
                title = it[CourseTable.title],
                coverImage = it[CourseTable.coverImage],
                primaryCoins = it[CourseTable.primaryCoins],
                secondaryCoins = it[CourseTable.secondaryCoins],
                description = it[CourseTable.description],
                instructor = it[CourseTable.instructor],
            )
        }.singleOrNull()
    }

    override suspend fun delete(id: String)
    : CourseEntity? = dbQuery {
        read(id)?.let { entity ->
            CourseTable.deleteWhere { CourseTable.id.eq(id) }.let { deleteResult ->
                if (deleteResult > 0) {
                    entity
                } else {
                    null
                }
            }
        }
    }

    override suspend fun update(id: String, entity: CourseEntity)
    : CourseEntity? = dbQuery {
        CourseTable.update({
            CourseTable.id.eq(id)
        }) {
            it[title] = entity.title
            it[coverImage] = entity.coverImage
            it[primaryCoins] = entity.primaryCoins
            it[secondaryCoins] = entity.secondaryCoins
            it[description] = entity.description
            it[instructor] = entity.instructor
        }.let { updateResult ->
            if (updateResult > 0) {
                entity.copy(id = id)
            } else {
                null
            }
        }
    }
}
