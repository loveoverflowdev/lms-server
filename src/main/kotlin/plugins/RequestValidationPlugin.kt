package plugins

import commands.CreateCourseCommand
import commands.GetCourseByIdCommand
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<CreateCourseCommand> { command ->
            if (command.title.isNullOrEmpty()) {
                ValidationResult.Invalid("Missing [title] in the request")
            } else if (command.description.isNullOrEmpty()) {
                ValidationResult.Invalid("Missing [description] in the request")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
