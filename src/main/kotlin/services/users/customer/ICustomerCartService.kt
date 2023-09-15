package services.users.customer

import commands.*
import models.products.course.Course
import models.products.group.CourseGroup

interface ICustomerCartService {
    suspend fun getCourseListInCart(command: GetCourseListInCustomerCartCommand)
    : Result<List<Course>>
    suspend fun getCourseGroupListInCart(command: GetCourseGroupListInCustomerCartCommand)
    : Result<List<CourseGroup>>
    suspend fun addCourseToCart(command: AddCourseToCustomerCartCommand)
    suspend fun addCourseGroupToCart(command: AddCourseGroupToCustomerCartCommand)
    suspend fun removeCourseFromCart(command: RemoveCourseFromCartCommand)
    suspend fun removeCourseGroupFromCart(command: RemoveCourseGroupFromCartCommand)
}
