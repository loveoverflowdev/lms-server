package routing.customer

import commands.GetCourseListInCustomerCartCommand
import dtos.products.course.CourseDTO
import dtos.response.ResponseDTO
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerCoinsRoutes() {
    route("customer-coins") {
        authenticate("auth-jwt") {
            get("") {
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
        }
    }
}
