package repositories.cart

import models.products.course.Course
import models.products.group.CourseGroup

interface ICartRepository {
    suspend fun getCartIdByUser(userId: String): String
    suspend fun getCourseListInCart(cartId: String): List<Course>
    suspend fun addCourseToCart(cartId: String, courseId: String)
    suspend fun removeCourseFromCart(cartId: String, courseId: String)
    suspend fun getCourseGroupListInCart(cartId: String): List<CourseGroup>
    suspend fun addCourseGroupToCart(cartId: String, courseGroupId: String)
    suspend fun removeCourseGroupFromCart(cartId: String, courseGroupId: String)
}
