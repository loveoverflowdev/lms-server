package services.users.admin

import commands.GrantCoinsToCustomerCommand
import models.users.Customer
import repositories.user.IUserRepository

class CoinsGrantService(
    private val userRepository: IUserRepository,
) : ICoinsGrantService {

    override suspend fun grantCoinsToCustomer(command: GrantCoinsToCustomerCommand): Result<Customer> {
        val updatedCustomer = userRepository.grantCoinsToCustomer(
            customerId = command.customerId ?: "",
            primaryCoins = command.primaryCoins ?: 0,
        )
        return Result.success(updatedCustomer)
    }
}
