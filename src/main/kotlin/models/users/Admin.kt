package models.users

import models.users.base.User

class Admin(
    id: String,
    password: String,
) : User(id, password)
