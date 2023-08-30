package models.products.group

import models.products.base.Product

class CourseGroup(
    id: String,
    title: String,
    coverImage: String,
    primaryCoins: Double,
    secondaryCoins: Double?,
    description: String,
): Product(
    id = id,
    title = title,
    coverImage = coverImage,
    primaryCoins = primaryCoins,
    secondaryCoins = secondaryCoins,
    description = description,
)