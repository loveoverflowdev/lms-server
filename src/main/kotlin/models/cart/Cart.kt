package models.cart

import models.base.Model
import models.products.base.Product
import models.users.base.User

class Cart(
    id: String,
    val user: User,
    val itemList: List<Product> = listOf(),
) : Model(id)
