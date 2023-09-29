package repositories.user

import database.DatabaseFactory
import database.schemas.cart.CartEntity
import database.schemas.cart.CartSchema
import database.schemas.user.*
import models.users.Admin
import models.users.Customer
import models.users.Seller

class UserRepository: IUserRepository {

    private val userSchema: UserSchema = UserSchema(
        database = DatabaseFactory.databaseShared
    )

    private val cartSchema: CartSchema = CartSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun authenticateAdmin(username: String, password: String): Admin {
        return userSchema.authenticateAdmin(username, password)
    }

    override suspend fun authenticateSeller(username: String, password: String): Seller {
        return userSchema.authenticateSeller(username, password)
    }

    override suspend fun authenticateCustomer(usernameOrEmail: String, password: String): Customer {
        return userSchema.authenticateCustomer(usernameOrEmail, password)
    }

    override suspend fun registerCustomer(email: String, phoneNumber: String, username: String, password: String)
    : Customer {
        return userSchema.registerCustomer(
            email = email,
            phoneNumber = phoneNumber,
            username = username,
            password = password
        ).apply {
            cartSchema.create(CartEntity(
                id = "",
                userId = this.id
            ))
        }
    }

    override suspend fun grantCoinsToCustomer(customerId: String, primaryCoins: Int): Customer {
        return userSchema.grantCoinsToCustomer(customerId, primaryCoins)
    }

    override suspend fun getCustomer(id: String): Customer? {
        return userSchema.readCustomer(id)
    }
}
