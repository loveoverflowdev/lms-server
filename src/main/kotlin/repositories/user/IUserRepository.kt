package repositories.user

import models.users.Admin
import models.users.Customer
import models.users.Seller

interface IUserRepository {
    suspend fun authenticateAdmin(username: String, password: String): Result<Admin>
    suspend fun authenticateSeller(username: String, password: String): Result<Seller>
    suspend fun authenticateCustomer(usernameOrEmail: String, password: String): Result<Customer>
    suspend fun registerCustomer(email: String, phoneNumber: String, username: String, password: String): Result<Customer>
}
