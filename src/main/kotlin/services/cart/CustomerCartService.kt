package services.cart

import commands.*
import models.products.course.Course
import models.products.group.CourseGroup
import repositories.cart.ICartRepository

class CustomerCartService(
    private val cartRepository: ICartRepository,
): ICustomerCartService {
    override suspend fun getCourseListInCart(command: GetCourseListInCustomerCartCommand): Result<List<Course>> {
        return cartRepository
            .getCartId(command.customerId)
            .let { cartId ->
                val courseList = cartRepository.getCourseListInCart(cartId)
                Result.success(courseList)
            }
    }

    override suspend fun getCourseGroupListInCart(command: GetCourseGroupListInCustomerCartCommand): Result<List<CourseGroup>> {
        return cartRepository
            .getCartId(command.customerId)
            .let { cartId ->
                val courseGroupList = cartRepository.getCourseGroupListInCart(cartId)
                Result.success(courseGroupList)
            }
    }

    override suspend fun addCourseToCart(command: AddCourseToCustomerCartCommand) {
        cartRepository
            .getCartId(command.customerId)
            .let { cartId ->
                cartRepository.addCourseToCart(cartId, command.courseId ?: "")
            }
    }

    override suspend fun addCourseGroupToCart(command: AddCourseGroupToCustomerCartCommand){
        cartRepository
            .getCartId(command.customerId)
            .let { cartId ->
                cartRepository.addCourseGroupToCart(cartId, command.courseGroupId ?: "")
            }
    }

    override suspend fun removeCourseFromCart(command: RemoveCourseFromCartCommand) {
        cartRepository
            .getCartId(command.customerId)
            .let { cartId ->
                cartRepository.removeCourseFromCart(cartId, command.courseId ?: "")
            }
    }

    override suspend fun removeCourseGroupFromCart(command: RemoveCourseGroupFromCartCommand) {
        return cartRepository
            .getCartId(command.customerId)
            .let { cartId ->
                cartRepository.removeCourseGroupFromCart(cartId, command.courseGroupId ?: "")
            }
    }
}
