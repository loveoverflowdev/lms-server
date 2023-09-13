package services.users

import commands.AdminLogInCommand
import commands.CustomerLogInCommand
import commands.CustomerRegisterCommand
import commands.SellerLogInCommand

import repositories.user.IUserRepository

import models.users.Admin
import models.users.Customer
import models.users.Seller


class UserService(
    private val userRepository: IUserRepository,
) : IUserService {
    override suspend fun authenticateAdmin(command: AdminLogInCommand): Result<Admin> {
        return userRepository.authenticateAdmin(
            username = command.username ?: "",
            password = command.password ?: "",
        )
    }

    override suspend fun authenticateSeller(command: SellerLogInCommand): Result<Seller> {
        return userRepository.authenticateSeller(
            username = command.username ?: "",
            password = command.password ?: "",
        )
    }

    override suspend fun authenticateCustomer(command: CustomerLogInCommand): Result<Customer> {
        return userRepository.authenticateCustomer(
            usernameOrEmail = command.usernameOrEmail ?: "",
            password = command.password ?: "",
        )
    }

    override suspend fun registerCustomer(command: CustomerRegisterCommand): Result<Customer> {
        return userRepository.registerCustomer(
            email = command.email ?: "",
            phoneNumber = command.phoneNumber ?: "",
            username = command.username ?: "",
            password = command.password ?: "",
        )
    }
}
