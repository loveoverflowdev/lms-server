package routing

import commands.*
import dtos.products.course.CourseDTO
import dtos.products.group.CourseGroupDTO
import dtos.response.ResponseDTO
import dtos.response.StatusDTO
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import repositories.cart.CartRepository
import repositories.cart.ICartRepository
import services.users.customer.CustomerCartService
import services.users.customer.ICustomerCartService

val cartRepository: ICartRepository = CartRepository()
val customerCartService: ICustomerCartService = CustomerCartService(cartRepository)

fun Route.customerCartRoutes() {
    route("/customer-cart") {
        authenticate("auth-jwt") {
            get("course-list") {
                val principal = call.principal<JWTPrincipal>()
                val customerId = principal!!.payload.getClaim("id").asString()
                customerCartService.getCourseListInCart(
                    GetCourseListInCustomerCartCommand(
                        customerId = customerId
                    )
                ).onSuccess {
                    val courseListDto = it.map { CourseDTO.of(it) }
                    call.respond(
                        ResponseDTO(
                            data = courseListDto
                        )
                    )
                }.onFailure { throw it }
            }

            get("course-group-list") {
                val principal = call.principal<JWTPrincipal>()
                val customerId = principal!!.payload.getClaim("id").asString()
                customerCartService.getCourseGroupListInCart(
                    GetCourseGroupListInCustomerCartCommand(
                        customerId = customerId
                    )
                ).onSuccess {
                    val courseGroupListDto = it.map { CourseGroupDTO.of(it) }
                    call.respond(
                        ResponseDTO(
                            data = courseGroupListDto
                        )
                    )
                }.onFailure { throw it }
            }

            post("course") {
                val principal = call.principal<JWTPrincipal>()
                val customerId = principal!!.payload.getClaim("id").asString()

                val command = call.receive<AddCourseToCustomerCartCommand>().copy(
                    customerId = customerId
                )
                customerCartService.addCourseToCart(command)
                call.respond(
                    ResponseDTO(
                        status = StatusDTO(
                            code = 200,
                        ),
                        data = null,
                    )
                )
            }

            delete("course/{id}") {
                val principal = call.principal<JWTPrincipal>()
                val customerId = principal!!.payload.getClaim("id").asString()
                val courseId = call.parameters["id"]

                val command = RemoveCourseFromCartCommand(
                    courseId = courseId,
                    customerId = customerId,
                )

                customerCartService.removeCourseFromCart(command)
                call.respond(
                    ResponseDTO(
                        status = StatusDTO(
                            code = 200,
                        ),
                        data = null,
                    )
                )
            }

            post("course-group") {
                val principal = call.principal<JWTPrincipal>()
                val customerId = principal!!.payload.getClaim("id").asString()

                val command = call.receive<AddCourseGroupToCustomerCartCommand>().copy(
                    customerId = customerId
                )
                customerCartService.addCourseGroupToCart(command)
                call.respond(
                    ResponseDTO(
                        status = StatusDTO(
                            code = 200,
                        ),
                        data = null,
                    )
                )
            }

            delete("course-group/{id}") {
                val principal = call.principal<JWTPrincipal>()
                val customerId = principal!!.payload.getClaim("id").asString()
                val courseGroupId = call.parameters["id"]

                val command = RemoveCourseGroupFromCartCommand(
                    courseGroupId = courseGroupId,
                    customerId = customerId,
                )

                customerCartService.removeCourseGroupFromCart(command)
                call.respond(
                    ResponseDTO(
                        status = StatusDTO(
                            code = 200,
                        ),
                        data = null,
                    )
                )
            }
        }
    }
}
