package repositories.user

import models.users.Admin
import models.users.Customer
import models.users.Seller

interface IUserRepository {
    suspend fun authenticateAdmin(username: String, password: String): Admin
    suspend fun authenticateSeller(username: String, password: String): Seller
    suspend fun authenticateCustomer(usernameOrEmail: String, password: String): Customer
    suspend fun registerCustomer(email: String, phoneNumber: String, username: String, password: String): Customer
    suspend fun grantCoinsToCustomer(customerId: String, primaryCoins: Int): Customer
    suspend fun getCustomer(id: String): Customer?
}
