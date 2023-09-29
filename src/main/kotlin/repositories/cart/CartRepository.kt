package repositories.cart;

import database.DatabaseFactory
import database.schemas.cart.CartSchema
import database.schemas.membership.CartCourseGroupMembershipSchema
import database.schemas.membership.CartCourseMembershipSchema
import models.products.course.Course
import models.products.group.CourseGroup

class CartRepository: ICartRepository {

    private val cartSchema: CartSchema = CartSchema(
        database = DatabaseFactory.databaseShared
    )

    private val cartCourseMembership: CartCourseMembershipSchema = CartCourseMembershipSchema(
        database = DatabaseFactory.databaseShared
    )

    private val cartCourseGroupMembershipSchema: CartCourseGroupMembershipSchema = CartCourseGroupMembershipSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun getCartId(userId: String): String {
        return cartSchema.getCartId(userId)
    }

    override suspend fun getCourseListInCart(cartId: String): List<Course> {
        return cartCourseMembership
            .getCourseListInCart(cartId)
            .map {
                Course(
                    id = it.id,
                    title = it.title,
                    coverImage = it.coverImage,
                    primaryCoins = it.primaryCoins,
                    secondaryCoins = it.secondaryCoins,
                    description = it.description,
                    instructor = it.instructor,
                )
            }
    }

    override suspend fun addCourseToCart(cartId: String, courseId: String) {
        cartCourseMembership.addCourseToCart(
            cartId = cartId,
            courseId = courseId,
        )
    }

    override suspend fun removeCourseFromCart(cartId: String, courseId: String) {
        cartCourseMembership.removeCourseFromCart(
            cartId = cartId,
            courseId = courseId,
        )
    }

    override suspend fun getCourseGroupListInCart(cartId: String): List<CourseGroup> {
        return cartCourseGroupMembershipSchema
            .getCourseGroupListInCart(cartId)
            .map {
                CourseGroup(
                    id = it.id,
                    title = it.title,
                    coverImage = it.coverImage,
                    primaryCoins = it.primaryCoins,
                    secondaryCoins = it.secondaryCoins,
                    description = it.description,
                )
            }
    }

    override suspend fun addCourseGroupToCart(cartId: String, courseGroupId: String) {
        cartCourseGroupMembershipSchema.addCourseGroupToCart(
            cartId = cartId,
            courseGroupId = courseGroupId,
        )
    }

    override suspend fun removeCourseGroupFromCart(cartId: String, courseGroupId: String) {
        cartCourseGroupMembershipSchema.removeCourseGroupFromCart(
            cartId = cartId,
            courseGroupId = courseGroupId,
        )
    }
}
