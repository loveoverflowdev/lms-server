package routing.general

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import commands.AdminLogInCommand
import commands.CustomerLogInCommand
import commands.CustomerRegisterCommand
import commands.SellerLogInCommand
import dtos.response.ResponseDTO
import dtos.response.TokensDTO
import dtos.users.CustomerDTO
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import repositories.user.IUserRepository
import java.util.*

import repositories.user.UserRepository
import services.auth.IAuthService
import services.auth.AuthService

val userRepository: IUserRepository = UserRepository()
val userService: IAuthService = AuthService(userRepository)

fun Route.authRoutes() {
    val secret = environment?.config?.property("jwt.secret")?.getString()
    val issuer = environment?.config?.property("jwt.issuer")?.getString()
    val audience = environment?.config?.property("jwt.audience")?.getString()
    route("/authentication") {
        route("customer") {
            post("login") {
                val command = call.receive<CustomerLogInCommand>()
                userService.authenticateCustomer(command)
                    .onSuccess {
                        val token = JWT.create()
                            .withAudience(audience)
                            .withIssuer(issuer)
                            .withClaim("id", it.id)
                            .withExpiresAt(Date(System.currentTimeMillis() + 108_000_000))
                            .sign(Algorithm.HMAC256(secret))

                        call.respond(
                            ResponseDTO(
                                data = CustomerDTO.of(it),
                                tokens = TokensDTO(
                                    accessToken = token,
                                    refreshToken = null,
                                )
                            )
                        )
                    }
                    .onFailure { throw it }
            }

            post("register") {
                val command = call.receive<CustomerRegisterCommand>()
                userService.registerCustomer(command)
                    .onSuccess {
                        call.respond(
                            ResponseDTO(
                                data = CustomerDTO(
                                    id = it.id,
                                    username = it.username,
                                    email = it.email,
                                    phoneNumber = it.phoneNumber,
                                    displayName = it.displayName,
                                    affiliateCode = it.affiliateCode,
                                    primaryCoins = it.primaryCoins,
                                )
                            )
                        )
                    }
                    .onFailure { throw it }
            }
        }

        route("admin") {
            post("login") {
                val command = call.receive<AdminLogInCommand>()
                userService.authenticateAdmin(command)
                    .onSuccess {
                        val token = JWT.create()
                            .withAudience(audience)
                            .withIssuer(issuer)
                            .withClaim("id", it.id)
                            .withExpiresAt(Date(System.currentTimeMillis() + 108_000_000))
                            .sign(Algorithm.HMAC256(secret))

                        call.respond(
                            ResponseDTO(
                                data = hashMapOf("token" to token)
                            )
                        )
                    }
                    .onFailure { throw it }

            }
        }

        route("seller") {
            post("login") {
                val command = call.receive<SellerLogInCommand>()
                userService.authenticateSeller(command)
                    .onSuccess {
                        val token = JWT.create()
                            .withAudience(audience)
                            .withIssuer(issuer)
                            .withClaim("id", it.id)
                            .withExpiresAt(Date(System.currentTimeMillis() + 108_000_000))
                            .sign(Algorithm.HMAC256(secret))

                        call.respond(
                            ResponseDTO(
                                data = hashMapOf("token" to token)
                            )
                        )
                    }
                    .onFailure { throw it }
            }
        }
    }
}
