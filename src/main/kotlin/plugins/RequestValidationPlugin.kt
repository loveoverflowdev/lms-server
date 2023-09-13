package plugins

import commands.AddCourseToGroupCommand
import commands.CreateCourseCommand
import commands.CustomerLogInCommand
import commands.UpdateCourseCommand
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<CustomerLogInCommand>() { command ->
            if (command.usernameOrEmail.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [username] or [email] in the request")
            } else if(command.password.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [password] in the request")
            } else {
                ValidationResult.Valid
            }
        }
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
        validate<UpdateCourseCommand> { command ->
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
        validate<AddCourseToGroupCommand>() { command ->
            if (command.courseGroupId.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [courseGroupId] in the request")
            } else if (command.id.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [courseId] in the request")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
