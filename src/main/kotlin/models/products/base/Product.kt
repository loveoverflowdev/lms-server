package models.products.base

import models.base.Model

abstract class Product(
    id: String,
    val title: String,
    val coverImage: String,
    val description: String,
    val primaryCoins: Double,
    val secondaryCoins: Double?
): Model(id)
