package models.cart

import models.base.Model

class Cart(
    id: String,
    val userId: String,
) : Model(id)
