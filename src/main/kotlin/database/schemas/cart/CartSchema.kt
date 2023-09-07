package database.schemas.cart

import database.schemas.base.BaseEntity
import database.schemas.base.BaseTable
import database.schemas.user.UserTable
import models.cart.Cart

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

class CartSchema {
}
