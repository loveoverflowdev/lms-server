package models.users

import models.users.base.User

class Seller(
    id: String,
    password: String,
) : User(id, password)
