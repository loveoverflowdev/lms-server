package database.schemas.products.group

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.products.course.CourseTable
import models.base.Model
import models.products.group.CourseGroup
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

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

    override fun toModel(): Model {
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
    override suspend fun create(entity: CourseGroupEntity): CourseGroupEntity = dbQuery {
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

    override suspend fun read(id: String): CourseGroupEntity? = dbQuery {
        CourseGroupTable.select {
            CourseGroupTable.id.eq(id)
        }.map {
            CourseGroupEntity(
                id = it[CourseTable.id].value,
                title = it[CourseTable.title],
                coverImage = it[CourseTable.coverImage],
                primaryCoins = it[CourseTable.primaryCoins],
                secondaryCoins = it[CourseTable.secondaryCoins],
                description = it[CourseTable.description],
            )
        }.singleOrNull()
    }

    override suspend fun update(id: String, entity: CourseGroupEntity): CourseGroupEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CourseGroupEntity? {
        TODO("Not yet implemented")
    }


}





