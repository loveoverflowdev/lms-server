package models.users

import models.users.base.User

class Customer(
    id: String,
    password: String,
) : User(id, password)
