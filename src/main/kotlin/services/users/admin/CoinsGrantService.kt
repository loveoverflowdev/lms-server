package services.users.admin

import commands.GrantCoinsToCustomerCommand
import repositories.user.IUserRepository

class CoinsGrantService(
    private val userRepository: IUserRepository,
) : ICoinsGrantService {

    override suspend fun grantCoinsToCustomer(command: GrantCoinsToCustomerCommand) {
        userRepository.grantCoinsToCustomer(
            customerId = command.customerId ?: "",
            primaryCoins = command.primaryCoins ?: 0,
        )
    }
}
