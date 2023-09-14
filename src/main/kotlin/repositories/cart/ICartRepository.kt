package repositories.cart

import models.products.base.Product
import models.products.course.Course
import models.products.group.CourseGroup
import repositories.base.IEntityRepository

interface ICartRepository: IEntityRepository<Product> {
    suspend fun getCourseListInCart(cartId: String): Result<List<Course>>
    suspend fun addCourseToCart(cartId: String, courseId: String)
    suspend fun removeCourseFromCart(cartId: String, courseId: String)
    suspend fun getCourseGroupListInCart(cartId: String): Result<List<CourseGroup>>
    suspend fun addCourseGroupToCart(cartId: String, courseGroupId: String)
    suspend fun removeCourseGroupFromCart(cartId: String, courseGroupId: String)
}
