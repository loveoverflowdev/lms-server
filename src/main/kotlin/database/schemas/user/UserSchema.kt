package database.schemas.user

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import models.users.Admin
import models.users.Customer
import models.users.Seller
import models.users.base.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

enum class UserRole(val value: String) {
    CUSTOMER("customer"),
    SELLER("seller"),
    ADMIN("admin")
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
                    affiliateCode = this.affiliateCode
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
    val role = enumeration<UserRole>("role")
    val displayName = varchar("display_name", length = 255)
    val affiliateCode = varchar("affiliate_code", length = 255)

    override val primaryKey = PrimaryKey(id)
}

class UserSchema(
    database: Database,
) : BaseSchema<UserTable, UserEntity>(database) {

    suspend fun authenticateUser(username: String, password: String, role: UserRole): UserEntity? = dbQuery {
        val hashedPassword =
        UserTable.select {
            UserTable.username.eq(username)
        }
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
                affiliateCode = this[UserTable.affiliateCode]
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
                affiliateCode = it[UserTable.affiliateCode]
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
