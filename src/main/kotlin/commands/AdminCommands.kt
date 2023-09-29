package commands

import com.google.gson.annotations.SerializedName

class AdminLogInCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value = "password")
    val password: String?,
)
