package routing.admin

import commands.GrantCoinsToCustomerCommand
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import repositories.user.IUserRepository
import repositories.user.UserRepository
import services.users.admin.CoinsGrantService
import services.users.admin.ICoinsGrantService

val userRepository: IUserRepository = UserRepository()
val coinsGrantService: ICoinsGrantService = CoinsGrantService(userRepository)

fun Route.coinsGrantRoutes() {
    route("/coins-grant") {
        authenticate("auth-jwt") {
            put {
                val command = call.receive<GrantCoinsToCustomerCommand>()
                coinsGrantService.grantCoinsToCustomer(
                    GrantCoinsToCustomerCommand(
                        customerId = command.customerId,
                        primaryCoins = command.primaryCoins,
                    )
                )
            }
        }
    }
}
