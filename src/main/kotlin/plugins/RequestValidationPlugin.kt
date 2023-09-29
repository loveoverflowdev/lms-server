package plugins

import commands.*
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
        validate<CustomerRegisterCommand>() { command ->
            if (command.username.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [username] in the request")
            } else if(command.password.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [password] in the request")
            } else if(command.phoneNumber.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [phoneNumber] in the request")
            } else if(command.email.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [email] in the request")
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

        validate<AddCourseToCustomerCartCommand>() { command ->
            if (command.courseId.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [courseId] in the request")
            } else {
                ValidationResult.Valid
            }
        }
        validate<AddCourseGroupToCustomerCartCommand>() { command ->
            if (command.courseGroupId.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [courseGroupId] in the request")
            } else {
                ValidationResult.Valid
            }
        }
        validate<GrantCoinsToCustomerCommand>() { command ->
            if (command.customerId.isNullOrBlank()) {
                ValidationResult.Invalid("Missing [customerId] in the request")
            } else if (command.primaryCoins == null) {
                ValidationResult.Invalid("Missing [customerId] in the request")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
