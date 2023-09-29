package services.users.customer

import commands.GetCustomerListCommand
import models.users.Customer

interface ICustomerService {
    suspend fun getCustomerList(command: GetCustomerListCommand)
    : Result<List<Customer>>
}
