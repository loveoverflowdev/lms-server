package services.auth

import commands.*
import models.products.base.Product
import models.users.Admin
import models.users.Customer
import models.users.Seller

interface IAuthService {
    suspend fun authenticateAdmin(command: AdminLogInCommand): Result<Admin>
    suspend fun authenticateSeller(command: SellerLogInCommand): Result<Seller>
    suspend fun authenticateCustomer(command: CustomerLogInCommand): Result<Customer>
    suspend fun registerCustomer(command: CustomerRegisterCommand): Result<Customer>
}
