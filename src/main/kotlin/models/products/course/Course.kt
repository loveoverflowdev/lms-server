package models.products.course

import models.products.base.Product

class Course(
    id: String,
    title: String,
    coverImage: String?,
    primaryCoins: Int,
    secondaryCoins: Int?,
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
