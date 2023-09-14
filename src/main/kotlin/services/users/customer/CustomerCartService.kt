package services.users.customer

import commands.*
import models.products.course.Course
import models.products.group.CourseGroup

class CustomerCartService(): ICustomerCartService {
    override suspend fun getCourseListInCart(command: GetCourseListInCustomerCartCommand): Result<List<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseGroupListInCart(command: GetCourseGroupListInCustomerCartCommand): Result<List<CourseGroup>> {
        TODO("Not yet implemented")
    }

    override suspend fun addCourseToCart(command: AddCourseToCustomerCartCommand): Result<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun addCourseGroupToCart(command: AddCourseGroupToCustomerCartCommand): Result<CourseGroup> {
        TODO("Not yet implemented")
    }
}
