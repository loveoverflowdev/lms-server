package models.users.base

import models.base.Model

abstract class User(
    id: String,
    val password: String,
): Model(id)
