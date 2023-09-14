package database.schemas.membership

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.course.CourseTable
import database.schemas.group.CourseGroupEntity
import database.schemas.group.CourseGroupTable
import models.base.Model
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.sql.SQLIntegrityConstraintViolationException

data class CartCourseGroupMembershipEntity(
    override val id: String,
    val cartId: String,
    val courseGroupId: String,
) : BaseEntity(id) {
    override fun toModel(): Model {
        TODO("Not yet implemented")
    }
}

object CartCourseGroupMembershipTable: BaseTable("cart_course_group_membership") {
    val courseGroupId = reference("course_group_id", id)
    val cartId = reference("cart_id", id)

    override val primaryKey = PrimaryKey(id)
}

class CartCourseGroupMembershipSchema(
    database: Database
) : BaseSchema<CartCourseGroupMembershipTable, CartCourseGroupMembershipEntity>(database) {

    suspend fun getCourseGroupListInCart(cartId: String) : List<CourseGroupEntity> = dbQuery {
        CourseGroupTable.innerJoin(
            CartCourseGroupMembershipTable,
            { CourseGroupTable.id},
            { CartCourseGroupMembershipTable.courseGroupId}
        ).select {
            CartCourseGroupMembershipTable.cartId.eq(cartId)
        }.map {
            CourseGroupEntity(
                id = it[CourseTable.id].value,
                title = it[CourseTable.title],
                coverImage = it[CourseTable.coverImage],
                primaryCoins = it[CourseTable.primaryCoins],
                secondaryCoins = it[CourseTable.secondaryCoins],
                description = it[CourseTable.description],
            )
        }
    }

    suspend fun addCourseGroupToCart(cartId: String, courseGroupId: String) {
        val membership = CartCourseGroupMembershipTable.select {
            CartCourseGroupMembershipTable.cartId.eq(cartId)
                .and(CartCourseGroupMembershipTable.courseGroupId.eq(courseGroupId))
        }.singleOrNull()

        membership?.apply {
            create(CartCourseGroupMembershipEntity(
                id = "",
                cartId = cartId,
                courseGroupId = courseGroupId,
            ))
        } ?: throw SQLIntegrityConstraintViolationException("this course group did exist in the cart")
    }

    suspend fun removeCourseGroupFromCart(cartId: String, courseGroupId: String) {
        // TODO: Change all hard-strings to Exceptions classes
        val deletedRowCount = CartCourseGroupMembershipTable.deleteWhere {
            CartCourseGroupMembershipTable.cartId.eq(cartId)
                .and(CartCourseGroupMembershipTable.courseGroupId.eq(courseGroupId))
        }
        if (deletedRowCount <= 0) {
            throw SQLIntegrityConstraintViolationException("this course group did not exist in the cart")
        }
    }

    override suspend fun create(entity: CartCourseGroupMembershipEntity)
            : CartCourseGroupMembershipEntity = dbQuery {
        CartCourseGroupMembershipTable.insert {
            it[courseGroupId] = entity.courseGroupId
            it[cartId] = entity.cartId
        }.run {
            CartCourseGroupMembershipEntity(
                id = this[CartCourseGroupMembershipTable.id].value,
                courseGroupId = this[CartCourseGroupMembershipTable.courseGroupId].value,
                cartId = this[CartCourseGroupMembershipTable.cartId].value
            )
        }
    }

    override suspend fun read(id: String): CartCourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CartCourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: CartCourseGroupMembershipEntity): CartCourseGroupMembershipEntity? {
        TODO("Not yet implemented")
    }
}
