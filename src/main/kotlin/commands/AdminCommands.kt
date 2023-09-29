package commands

import com.google.gson.annotations.SerializedName

class AdminLogInCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value = "password")
    val password: String?,
)

class GetCustomerListCommand()

class GetCustomerByIdCommand(
    @SerializedName(value = "id")
    val id: String,
)

class GrantCoinsToCustomerCommand(
    @SerializedName(value = "customerId")
    val customerId: String?,

    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
)
