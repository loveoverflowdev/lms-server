package services.users.customer

import commands.*
import models.products.course.Course
import models.products.group.CourseGroup
import repositories.cart.ICartRepository

class CustomerCartService(
    val cartRepository: ICartRepository,
): ICustomerCartService {
    override suspend fun getCourseListInCart(command: GetCourseListInCustomerCartCommand): Result<List<Course>> {
        return cartRepository
            .getCartIdByUser(command.customerId)
            ?.let { cartId ->
                val courseList = cartRepository.getCourseListInCart(cartId)
                Result.success(courseList)
            } ?: Result.failure(Exception("Not found cartId corresponding customerId: ${command.customerId}"))
    }

    override suspend fun getCourseGroupListInCart(command: GetCourseGroupListInCustomerCartCommand): Result<List<CourseGroup>> {
        return cartRepository
            .getCartIdByUser(command.customerId)
            ?.let { cartId ->
                val courseGroupList = cartRepository.getCourseGroupListInCart(cartId)
                Result.success(courseGroupList)
            } ?: Result.failure(Exception("Not found cartId corresponding customerId: ${command.customerId}"))
    }

    override suspend fun addCourseToCart(command: AddCourseToCustomerCartCommand): Result<String> {
        return cartRepository
            .getCartIdByUser(command.customerId)
            ?.let { cartId ->
                cartRepository.addCourseGroupToCart(cartId, command.courseId)
                Result.success("Successfully")
            } ?: Result.failure(Exception("Not found cartId corresponding customerId: ${command.customerId}"))
    }

    override suspend fun addCourseGroupToCart(command: AddCourseGroupToCustomerCartCommand): Result<String> {
        return cartRepository
            .getCartIdByUser(command.customerId)
            ?.let { cartId ->
                cartRepository.addCourseGroupToCart(cartId, command.courseGroupId)
                Result.success("Successfully")
            } ?: Result.failure(Exception("Not found cartId corresponding customerId: ${command.customerId}"))
    }

    override suspend fun removeCourseFromCart(command: RemoveCourseFromCartCommand): Result<String> {
        return cartRepository
            .getCartIdByUser(command.customerId)
            ?.let { cartId ->
                cartRepository.addCourseGroupToCart(cartId, command.courseId)
                Result.success("Successfully")
            } ?: Result.failure(Exception("Not found cartId corresponding customerId: ${command.customerId}"))
    }

    override suspend fun removeCourseGroupFromCart(command: RemoveCourseGroupFromCartCommand): Result<String> {
        return cartRepository
            .getCartIdByUser(command.customerId)
            ?.let { cartId ->
                cartRepository.removeCourseFromCart(cartId, command.courseGroupId)
                Result.success("Successfully")
            } ?: Result.failure(Exception("Not found cartId corresponding customerId: ${command.customerId}"))
    }
}
