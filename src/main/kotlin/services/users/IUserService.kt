package services.users

import commands.AdminLogInCommand
import commands.CustomerLogInCommand
import commands.CustomerRegisterCommand
import commands.SellerLogInCommand
import models.users.Admin
import models.users.Customer
import models.users.Seller

interface IUserService {
    suspend fun authenticateAdmin(command: AdminLogInCommand): Result<Admin>
    suspend fun authenticateSeller(command: SellerLogInCommand): Result<Seller>
    suspend fun authenticateCustomer(command: CustomerLogInCommand): Result<Customer>
    suspend fun registerCustomer(command: CustomerRegisterCommand): Result<Customer>
}
