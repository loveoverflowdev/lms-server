package services.users.base

abstract class User(
    val id: String,
    val displayName: String,
    val username: String,
    val hashedPassword: String
)
