package dtos.users

import com.google.gson.annotations.SerializedName

class CustomerDTO(
    @SerializedName(value = "id")
    val id: String,

    @SerializedName(value = "username")
    val username: String,

    @SerializedName(value = "displayName")
    val displayName: String,

    @SerializedName(value = "email")
    val email: String,

    @SerializedName(value = "phoneNumber")
    val phoneNumber: String,

    @SerializedName(value = "affilicateCode")
    val affiliateCode: String,
)
