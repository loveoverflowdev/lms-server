package services.auth

import commands.*
import repositories.user.IUserRepository

import models.users.Admin
import models.users.Customer
import models.users.Seller

class AuthService(
    private val userRepository: IUserRepository,
) : IAuthService {
    override suspend fun authenticateCustomer(command: CustomerLogInCommand): Result<Customer> {
        return userRepository.authenticateCustomer(
            usernameOrEmail = command.usernameOrEmail ?: "",
            password = command.password ?: "",
        ).run {
            Result.success(this)
        }
    }

    override suspend fun registerCustomer(command: CustomerRegisterCommand): Result<Customer> {
        return userRepository.registerCustomer(
            email = command.email ?: "",
            phoneNumber = command.phoneNumber ?: "",
            username = command.username ?: "",
            password = command.password ?: "",
        ).run {
            Result.success(this)
        }
    }

    override suspend fun authenticateAdmin(command: AdminLogInCommand): Result<Admin> {
        return userRepository.authenticateAdmin(
            username = command.username ?: "",
            password = command.password ?: "",
        ).run {
            Result.success(this)
        }
    }

    override suspend fun authenticateSeller(command: SellerLogInCommand): Result<Seller> {
        return userRepository.authenticateSeller(
            username = command.username ?: "",
            password = command.password ?: "",
        ).run {
            Result.success(this)
        }
    }
}
