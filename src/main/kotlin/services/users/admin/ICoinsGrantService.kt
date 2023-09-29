package services.users.admin

import commands.GrantCoinsToCustomerCommand
import models.users.Customer

interface ICoinsGrantService {
    suspend fun grantCoinsToCustomer(command: GrantCoinsToCustomerCommand): Result<Customer>
}
