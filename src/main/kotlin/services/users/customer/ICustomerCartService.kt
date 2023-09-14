package services.users.customer

import commands.AddCourseGroupToCustomerCartCommand
import commands.AddCourseToCustomerCartCommand
import commands.GetCourseGroupListInCustomerCartCommand
import commands.GetCourseListInCustomerCartCommand
import models.products.course.Course
import models.products.group.CourseGroup

interface ICustomerCartService {
    suspend fun getCourseListInCart(command: GetCourseListInCustomerCartCommand)
    : Result<List<Course>>

    suspend fun getCourseGroupListInCart(command: GetCourseGroupListInCustomerCartCommand)
    : Result<List<CourseGroup>>

    suspend fun addCourseToCart(command: AddCourseToCustomerCartCommand)
    : Result<Course>

    suspend fun addCourseGroupToCart(command: AddCourseGroupToCustomerCartCommand)
    : Result<CourseGroup>
}
