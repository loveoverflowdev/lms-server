package services.users

import services.users.base.User

class Admin(
    id: String,
    displayName: String,
    username: String,
    hashedPassword: String,
): User(
    id = id,
    username = username,
    hashedPassword = hashedPassword,
    displayName = displayName,
)
