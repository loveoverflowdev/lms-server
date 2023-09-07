package models.users.base

import models.base.Model

abstract class User(
    id: String,
    val displayName: String,
    val username: String,
    val hashedPassword: String
) : Model(id)
