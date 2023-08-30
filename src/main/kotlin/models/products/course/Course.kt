package models.products.course

import models.products.base.Product

class Course(
    id: String,
    title: String,
    coverImage: String,
    primaryCoins: Double,
    secondaryCoins: Double?,
    description: String,

    val instructor: String
): Product(
    id = id,
    title = title,
    coverImage = coverImage,
    primaryCoins = primaryCoins,
    secondaryCoins = secondaryCoins,
    description = description,
)
