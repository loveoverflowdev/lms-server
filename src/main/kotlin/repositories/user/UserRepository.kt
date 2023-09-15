package repositories.user

import database.DatabaseFactory
import database.schemas.user.*
import models.users.Admin
import models.users.Customer
import models.users.Seller
import models.users.base.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.mindrot.jbcrypt.BCrypt
import javax.naming.AuthenticationException


class UserRepository: IUserRepository {

    private val userSchema: UserSchema = UserSchema(
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

    override suspend fun registerCustomer(email: String, phoneNumber: String, username: String, password: String): Customer {
        val salt = BCrypt.gensalt(5) // Generate a salt
        val hashedPassword = BCrypt.hashpw(password, salt)
        val user = userSchema.create(UserEntity(
            id = "",
            affiliateCode = "",
            email = email,
            phoneNumber = phoneNumber,
            username = username,
            displayName = username,
            hashedPassword = hashedPassword,
            role = UserRole.CUSTOMER,
        ))
        return Customer(
            id = user.id,
            affiliateCode = user.affiliateCode,
            email = user.email,
            phoneNumber = user.phoneNumber,
            username = user.username,
            displayName = user.displayName,
            hashedPassword = user.hashedPassword,
        )
    }
}
