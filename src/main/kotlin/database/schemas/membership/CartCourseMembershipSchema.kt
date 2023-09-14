package database.schemas.membership

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.cart.CartTable
import database.schemas.course.CourseEntity
import database.schemas.course.CourseTable
import models.base.Model
import org.jetbrains.exposed.sql.*

data class CartCourseMembershipEntity(
    override val id: String,
    val cartId: String,
    val courseId: String,
) : BaseEntity(id) {
    override fun toModel(): Model {
        TODO("Not yet implemented")
    }
}

object CartCourseMembershipTable: BaseTable("cart_course_membership") {
    val courseId = reference("course_id", CourseTable.id)
    val cartId = reference("cart_id", CartTable.id)

    override val primaryKey = PrimaryKey(id)
}

class CartCourseMembershipSchema(
    database: Database
) : BaseSchema<CartCourseMembershipTable, CartCourseMembershipEntity>(database) {

    suspend fun getCourseListInCart(cartId: String) : List<CourseEntity> = dbQuery { {CartCourseMembershipTable.courseId}
        CourseTable.innerJoin(
            CartCourseMembershipTable,
            {CourseTable.id},
            {CartCourseMembershipTable.courseId}
        ).select {
            CartCourseMembershipTable.cartId.eq(cartId)
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
        }
    }

    suspend fun addCourseToCard(cartId: String, courseId: String) {
        create(CartCourseMembershipEntity(
            id = "",
            cartId = cartId,
            courseId = courseId,
        ))
    }

    override suspend fun create(entity: CartCourseMembershipEntity)
    : CartCourseMembershipEntity = dbQuery {
        CartCourseMembershipTable.insert {
            it[courseId] = entity.courseId
            it[cartId] = entity.cartId
        }.run {
            CartCourseMembershipEntity(
                id = this[CartCourseMembershipTable.id].value,
                courseId = this[CartCourseMembershipTable.courseId].value,
                cartId = this[CartCourseMembershipTable.cartId].value
            )
        }
    }

    override suspend fun read(id: String): CartCourseMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CartCourseMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: CartCourseMembershipEntity): CartCourseMembershipEntity? {
        TODO("Not yet implemented")
    }
}
