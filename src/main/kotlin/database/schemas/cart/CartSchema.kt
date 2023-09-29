package database.schemas.cart

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.user.UserTable
import models.cart.Cart
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.sql.SQLException

data class CartEntity(
    override val id: String,
    val userId: String,
) : BaseEntity(id) {
    override fun toModel(): Cart {
        return Cart(
            id = this.id,
            userId = this.userId,
        )
    }

    companion object {
        fun of(model: Cart): CartEntity {
            return CartEntity(
                id = model.id,
                userId = model.id,
            )
        }
    }
}

object CartTable: BaseTable("cart") {
    val userId = reference("user_id", UserTable.id)

    override val primaryKey = PrimaryKey(id)
}

class CartSchema(
    database: Database
) : BaseSchema<CartTable, CartEntity>(database) {

    suspend fun getCartId(userId: String): String = dbQuery {
        CartTable.select {
            CartTable.userId.eq(userId)
        }
            .singleOrNull()
            ?.run {
                this[CartTable.id].value
            } ?: throw SQLException("Not found cart id by user id: $userId")
    }

    public override suspend fun create(entity: CartEntity): CartEntity = dbQuery {
        CartTable.insert {
            it[userId] = entity.userId
        }.run {
            CartEntity(
                userId = this[CartTable.userId].value,
                id = this[CartTable.id].value,
            )
        }
    }

    override suspend fun read(id: String): CartEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CartEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: CartEntity): CartEntity? {
        TODO("Not yet implemented")
    }
}
