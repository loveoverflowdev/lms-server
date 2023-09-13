package services.users

import models.users.Admin
import models.users.Customer
import models.users.Seller
import repositories.user.IUserRepository

class UserService(
    private val userRepository: IUserRepository,
) : IUserService {
    override suspend fun authenticateAdmin(): Result<Admin> {
        TODO("Not yet implemented") // return userRepository.authenticateAdmin()
    }

    override suspend fun authenticateSeller(): Result<Seller> {
        TODO("Not yet implemented")
    }

    override suspend fun authenticateCustomer(): Result<Customer> {
        TODO("Not yet implemented")
    }
}
