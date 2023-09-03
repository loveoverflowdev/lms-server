package models.products.base

import models.base.Model

abstract class Product(
    id: String,
    val title: String,
    val coverImage: String?,
    val description: String,
    val primaryCoins: Int,
    val secondaryCoins: Int?
): Model(id)
