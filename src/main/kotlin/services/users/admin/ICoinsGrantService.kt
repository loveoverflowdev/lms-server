package services.users.admin

import commands.GrantCoinsToCustomerCommand

interface ICoinsGrantService {
    suspend fun grantCoinsToCustomer(command: GrantCoinsToCustomerCommand)
}
