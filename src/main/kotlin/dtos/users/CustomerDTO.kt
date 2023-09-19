package dtos.users

import com.google.gson.annotations.SerializedName
import models.users.Customer

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

    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int,
) {
    companion object {
        fun of(model: Customer): CustomerDTO {
            return CustomerDTO(
                id = model.id,
                username = model.username,
                displayName = model.displayName,
                email = model.email,
                phoneNumber = model.phoneNumber,
                affiliateCode = model.affiliateCode,
                primaryCoins = model.primaryCoins,
            )
        }
    }
}
