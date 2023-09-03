package plugins

import commands.CreateCourseCommand
import commands.GetCourseByIdCommand
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<CreateCourseCommand> { command ->
            if (command.title.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [title] in the request")
            } else if (command.description.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [description] in the request")
            } else if (command.primaryCoins == null){
                ValidationResult.Invalid("Missing [primaryCoins] in the request")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
