package services.users

import models.users.Admin
import models.users.Customer
import models.users.Seller

interface IUserService {
    suspend fun authenticateAdmin(): Result<Admin>

    suspend fun authenticateSeller(): Result<Seller>

    suspend fun authenticateCustomer(): Result<Customer>
}
