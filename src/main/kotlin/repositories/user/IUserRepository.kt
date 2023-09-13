package repositories.user

import models.users.Admin
import models.users.Customer
import models.users.Seller
import models.users.base.User

import repositories.base.IEntityRepository

interface IUserRepository: IEntityRepository<User> {
    suspend fun authenticateAdmin(username: String, password: String): Result<Admin>

    suspend fun authenticateSeller(username: String, password: String): Result<Seller>

    suspend fun authenticateCustomer(usernameOrEmail: String, password: String): Result<Customer>
}
