package models.users

import models.users.base.User

class Seller(
    id: String,
    displayName: String,
    username: String,
    hashedPassword: String,
    val email: String,
    val phoneNumber: String,
    val affiliateCode: String,
) : User(
    id = id,
    displayName = displayName,
    username = username,
    hashedPassword = hashedPassword
)
