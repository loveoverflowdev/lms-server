package services.users.customer

import commands.GetCustomerListCommand
import models.users.Customer

class CustomerService() : ICustomerService {
    override suspend fun getCustomerList(command: GetCustomerListCommand)
    : Result<List<Customer>> {
        TODO("Not yet implemented")
    }
}
