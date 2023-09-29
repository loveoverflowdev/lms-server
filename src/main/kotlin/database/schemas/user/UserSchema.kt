package database.schemas.user

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import io.ktor.server.plugins.*
import models.users.Admin
import models.users.Customer
import models.users.Seller
import models.users.base.User
import org.jetbrains.exposed.sql.*
import org.mindrot.jbcrypt.BCrypt
import javax.naming.AuthenticationException

enum class UserRole(
    private val rawValue: String,
) {
    CUSTOMER("customer"),
    SELLER("seller"),
    ADMIN("admin");

    override fun toString(): String {
        return rawValue
    }
}

data class UserEntity(
    override val id: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val hashedPassword: String,
    val role: UserRole,
    val displayName: String,
    val affiliateCode: String,
    val primaryCoins: Int?,
) : BaseEntity(id) {
    override fun toModel(): User {
        return when (role) {
            UserRole.CUSTOMER -> {
                Customer(
                    id = this.id,
                    username = this.username,
                    email = this.email,
                    phoneNumber = this.phoneNumber,
                    hashedPassword = this.hashedPassword,
                    displayName = this.displayName,
                    affiliateCode = this.affiliateCode,
                    primaryCoins = this.primaryCoins ?: 0,
                )
            }
            UserRole.SELLER -> {
                Seller(
                    id = this.id,
                    username = this.username,
                    email = this.email,
                    phoneNumber = this.phoneNumber,
                    hashedPassword = this.hashedPassword,
                    displayName = this.displayName,
                    affiliateCode = this.affiliateCode
                )
            }
            UserRole.ADMIN -> {
                Admin(
                    id = this.id,
                    username = this.username,
                    hashedPassword = this.hashedPassword,
                    displayName = this.displayName,
                )
            }
        }
    }
}

object UserTable: BaseTable("user") {
    val username = varchar("username", length = 255)
    val email = varchar("email", length = 255)
    val phoneNumber = varchar("phone_number", length = 255)
    val hashedPassword = varchar("hashed_password", length = 255)
    val role = enumerationByName<UserRole>("role", length = 25)
    val displayName = varchar("display_name", length = 255)
    val affiliateCode = varchar("affiliate_code", length = 255)
    val primaryCoins = integer("primary_coins").nullable()

    override val primaryKey = PrimaryKey(id)
}

class UserSchema(
    database: Database,
) : BaseSchema<UserTable, UserEntity>(database) {
    private fun isPasswordValid(inputPassword: String, storedHashedPassword: String): Boolean {

        return BCrypt.checkpw(inputPassword, storedHashedPassword)
    }
    suspend fun authenticateAdmin(username: String, password: String)
    : Admin = dbQuery {
            val queriedUser = UserTable.select {
                UserTable.username.eq(username)
                UserTable.role.eq(UserRole.ADMIN)
            }.singleOrNull()

            if (queriedUser != null) {
                val storedHashedPassword = queriedUser[UserTable.hashedPassword]
                if (isPasswordValid(password, storedHashedPassword)) {
                    Admin(
                        id = queriedUser[UserTable.id].value,
                        displayName = queriedUser[UserTable.displayName],
                        username = queriedUser[UserTable.username],
                        hashedPassword = ""
                    )
                } else {
                    throw AuthenticationException("Mật khẩu không đúng")
                }
            } else {
                throw AuthenticationException("Tên đăng nhập không tồn tại")
            }
    }

    suspend fun authenticateSeller(username: String, password: String)
    : Seller = dbQuery {
        val queriedUser = UserTable.select {
            UserTable.username.eq(username)
            UserTable.role.eq(UserRole.SELLER)
        }.singleOrNull()

        if (queriedUser != null) {
            val storedHashedPassword = queriedUser[UserTable.hashedPassword]
            if (isPasswordValid(password, storedHashedPassword)) {
                Seller(
                    id = queriedUser[UserTable.id].value,
                    displayName = queriedUser[UserTable.displayName],
                    username = queriedUser[UserTable.username],
                    email = queriedUser[UserTable.email],
                    phoneNumber = queriedUser[UserTable.phoneNumber],
                    affiliateCode = queriedUser[UserTable.affiliateCode],
                    hashedPassword = ""
                )
            } else {
                throw AuthenticationException("Mật khẩu không đúng")
            }
        } else {
            throw AuthenticationException("Tên đăng nhập không tồn tại")
        }
    }


    suspend fun authenticateCustomer(usernameOrEmail: String, password: String)
    : Customer = dbQuery {
        val queriedUser = UserTable.select {
            (UserTable.username.eq(usernameOrEmail) or UserTable.email.eq(usernameOrEmail))
                .and(UserTable.role.eq(UserRole.CUSTOMER))
        }.singleOrNull()

        if (queriedUser != null) {
            val storedHashedPassword = queriedUser[UserTable.hashedPassword]
            if (isPasswordValid(password, storedHashedPassword)) {
                Customer(
                    id = queriedUser[UserTable.id].value,
                    displayName = queriedUser[UserTable.displayName],
                    username = queriedUser[UserTable.username],
                    email = queriedUser[UserTable.email],
                    phoneNumber = queriedUser[UserTable.phoneNumber],
                    affiliateCode = queriedUser[UserTable.affiliateCode],
                    primaryCoins = queriedUser[UserTable.primaryCoins] ?: 0,
                    hashedPassword = "",
                )
            } else {
                throw AuthenticationException("Mật khẩu không đúng")
            }
        } else {
            throw AuthenticationException("Tên đăng nhập hoặc email không tồn tại")
        }
    }

    suspend fun registerCustomer(email: String, phoneNumber: String, username: String, password: String)
    : Customer {
        val salt = BCrypt.gensalt(5) // Generate a salt
        val hashedPassword = BCrypt.hashpw(password, salt)
        val user = create(UserEntity(
            id = "",
            affiliateCode = "",
            email = email,
            phoneNumber = phoneNumber,
            username = username,
            displayName = username,
            hashedPassword = hashedPassword,
            role = UserRole.CUSTOMER,
            primaryCoins = 200,
        ))
        return Customer(
            id = user.id,
            affiliateCode = user.affiliateCode,
            email = user.email,
            phoneNumber = user.phoneNumber,
            username = user.username,
            displayName = user.displayName,
            hashedPassword = user.hashedPassword,
            primaryCoins = 0,
        )
    }

    suspend fun readCustomer(id: String): Customer? {
        val user = read(id = id)
        if (user?.role != UserRole.CUSTOMER) {
            return null
        }
        return Customer(
            id = user.id,
            displayName = user.displayName,
            username = user.username,
            hashedPassword = user.hashedPassword,
            email = user.email,
            phoneNumber = user.phoneNumber,
            primaryCoins = user.primaryCoins ?: 0,
            affiliateCode = user.affiliateCode,
        )
    }

    suspend fun grantCoinsToCustomer(customerId: String, primaryCoins: Int) {
        val user = read(id = customerId)
        val customer = if (user?.role == UserRole.CUSTOMER) user else throw NotFoundException("Not found customer")
        update(customerId, entity = customer.copy(primaryCoins = primaryCoins))
    }

    override suspend fun create(entity: UserEntity)
    : UserEntity = dbQuery {
        UserTable.insert {
            it[username] = entity.username
            it[email] = entity.email
            it[phoneNumber] = entity.phoneNumber
            it[hashedPassword] = entity.hashedPassword
            it[role] = entity.role
            it[displayName] = entity.displayName
            it[affiliateCode] = entity.affiliateCode
        }.run {
            UserEntity(
                id = this[UserTable.id].value,
                username = this[UserTable.username],
                email = this[UserTable.email],
                phoneNumber = this[UserTable.phoneNumber],
                hashedPassword = this[UserTable.hashedPassword],
                role = this[UserTable.role],
                displayName = this[UserTable.displayName],
                affiliateCode = this[UserTable.affiliateCode],
                primaryCoins = this[UserTable.primaryCoins],
            )
        }
    }

    override suspend fun read(id: String)
    : UserEntity? = dbQuery {
        UserTable.select {
            UserTable.id.eq(id)
        }.map {
            UserEntity(
                id = it[UserTable.id].value,
                username = it[UserTable.username],
                email = it[UserTable.email],
                phoneNumber = it[UserTable.phoneNumber],
                hashedPassword = it[UserTable.hashedPassword],
                role = it[UserTable.role],
                displayName = it[UserTable.displayName],
                affiliateCode = it[UserTable.affiliateCode],
                primaryCoins = it[UserTable.primaryCoins],
            )
        }.singleOrNull()
    }

    override suspend fun delete(id: String): UserEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: UserEntity): UserEntity? {
        TODO("Not yet implemented")
    }
}
