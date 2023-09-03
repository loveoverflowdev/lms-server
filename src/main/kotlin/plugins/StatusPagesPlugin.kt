package plugins

import dtos.response.ResponseDTO
import dtos.response.StatusDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import java.sql.SQLException

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<Throwable> { call, error ->
            val httpStatus = HttpStatusCode.InternalServerError
            call.respond(
                httpStatus, ResponseDTO(
                    status = StatusDTO(
                        httpStatus.value,
                        message = "${ErrorMessage.INTERNAL_SERVER_ERROR} : ${error.message}"
                    ),
                    data = null
                )
            )
        }
        exception<RequestValidationException> { call, error ->
            val httpStatus = HttpStatusCode.BadRequest
            call.respond(
                httpStatus, ResponseDTO(
                    status = StatusDTO(
                        httpStatus.value,
                        message = error.reasons.joinToString(" ")
                    ),
                    data = null
                )
            )
        }
        exception<SQLException>() { call, error ->
            val httpStatus = HttpStatusCode.InternalServerError
            call.respond(
                httpStatus, ResponseDTO(
                    status = StatusDTO(
                        httpStatus.value,
                        message = error.message.toString()
                    ),
                    data = null
                )
            )
        }
    }
}

object ErrorMessage {
    const val UNAUTHORIZED = "Unauthorized api call"
    const val INTERNAL_SERVER_ERROR = "Internal server error"
    const val BAD_REQUEST = "Parameter mismatch"
    const val USER_NOT_EXIT = "User not exist"
    const val USER_TYPE_IS_NOT_VALID = "UserType is not valid"
    const val EMAIL_NOT_EXIST = "User not exist"
    const val PASSWORD_IS_WRONG = "Password is wrong"
    const val TYPE_CAST_EXCEPTION = "Type cast exception"
    const val NULL_POINTER_ERROR = "Null pointer error : "
}
