package repositories.cart;

import database.DatabaseFactory
import database.schemas.cart.CartSchema
import database.schemas.membership.CartCourseGroupMembershipSchema
import database.schemas.membership.CartCourseMembershipSchema
import models.products.base.Product
import models.products.course.Course
import models.products.group.CourseGroup

class CartRepository: ICartRepository {
    private val cartCourseMembership: CartCourseMembershipSchema = CartCourseMembershipSchema(
        database = DatabaseFactory.databaseShared
    )

    private val cartCourseGroupMembershipSchema: CartCourseGroupMembershipSchema = CartCourseGroupMembershipSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun getCourseListInCart(cartId: String): Result<List<Course>> {
        val courseList = cartCourseMembership
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
        return Result.success(courseList)
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

    override suspend fun getCourseGroupListInCart(cartId: String): Result<List<CourseGroup>> {
        val courseGroupList = cartCourseGroupMembershipSchema
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
        return Result.success(courseGroupList)
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


    override suspend fun getAll(): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): Result<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (Product) -> Boolean): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: Product): Result<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Result<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, model: Product): Result<Product> {
        TODO("Not yet implemented")
    }
}
