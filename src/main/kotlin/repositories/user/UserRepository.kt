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

    private fun isPasswordValid(inputPassword: String, storedHashedPassword: String): Boolean {
        return BCrypt.checkpw(inputPassword, storedHashedPassword)
    }

    override suspend fun authenticateAdmin(username: String, password: String): Result<Admin> {
        return userSchema.dbQuery {
            val queriedUser = UserTable.select {
                UserTable.username.eq(username)
                UserTable.role.eq(UserRole.ADMIN)
            }.singleOrNull()

            if (queriedUser != null) {
                val storedHashedPassword = queriedUser[UserTable.hashedPassword]
                if (isPasswordValid(password, storedHashedPassword)) {
                   Result.success(
                       Admin(
                           id = queriedUser[UserTable.id].value,
                           displayName = queriedUser[UserTable.displayName],
                           username = queriedUser[UserTable.username],
                           hashedPassword = ""
                       )
                   )
                } else {
                    Result.failure(AuthenticationException("Mật khẩu không đúng"))
                }
            } else {
                Result.failure(AuthenticationException("Tên đăng nhập không tồn tại"))
            }
        }
    }

    override suspend fun authenticateSeller(username: String, password: String): Result<Seller> {
        return userSchema.dbQuery {
            val queriedUser = UserTable.select {
                UserTable.username.eq(username)
                UserTable.role.eq(UserRole.SELLER)
            }.singleOrNull()

            if (queriedUser != null) {
                val storedHashedPassword = queriedUser[UserTable.hashedPassword]
                if (isPasswordValid(password, storedHashedPassword)) {
                    Result.success(
                        Seller(
                            id = queriedUser[UserTable.id].value,
                            displayName = queriedUser[UserTable.displayName],
                            username = queriedUser[UserTable.username],
                            email = queriedUser[UserTable.email],
                            phoneNumber = queriedUser[UserTable.phoneNumber],
                            affiliateCode = queriedUser[UserTable.affiliateCode],
                            hashedPassword = ""
                        )
                    )
                } else {
                    Result.failure(AuthenticationException("Mật khẩu không đúng"))
                }
            } else {
                Result.failure(AuthenticationException("Tên đăng nhập không tồn tại"))
            }
        }
    }

    override suspend fun authenticateCustomer(usernameOrEmail: String, password: String): Result<Customer> {
        return userSchema.dbQuery {
            val queriedUser = UserTable.select {
                (UserTable.username.eq(usernameOrEmail) or UserTable.email.eq(usernameOrEmail))
                    .and(UserTable.role.eq(UserRole.CUSTOMER))
            }.singleOrNull()

            if (queriedUser != null) {
                val storedHashedPassword = queriedUser[UserTable.hashedPassword]
                if (isPasswordValid(password, storedHashedPassword)) {
                    Result.success(
                        Customer(
                            id = queriedUser[UserTable.id].value,
                            displayName = queriedUser[UserTable.displayName],
                            username = queriedUser[UserTable.username],
                            email = queriedUser[UserTable.email],
                            phoneNumber = queriedUser[UserTable.phoneNumber],
                            affiliateCode = queriedUser[UserTable.affiliateCode],
                            hashedPassword = ""
                        )
                    )
                } else {
                    Result.failure(AuthenticationException("Mật khẩu không đúng"))
                }
            } else {
                Result.failure(AuthenticationException("Tên đăng nhập hoặc email không tồn tại"))
            }
        }
    }

    override suspend fun registerCustomer(email: String, phoneNumber: String, username: String, password: String): Result<Customer> {
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
        return Result.success(
            Customer(
                id = user.id,
                affiliateCode = user.affiliateCode,
                email = user.email,
                phoneNumber = user.phoneNumber,
                username = user.username,
                displayName = user.displayName,
                hashedPassword = user.hashedPassword,
            )
        )
    }
}
