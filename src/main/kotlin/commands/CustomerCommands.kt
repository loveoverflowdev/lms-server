package commands

import com.google.gson.annotations.SerializedName

class CustomerLogInCommand(
    @SerializedName(value = "usernameOrEmail")
    val usernameOrEmail: String?,
    @SerializedName(value = "password")
    val password: String?,
)

class CustomerRegisterCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value="email")
    val email: String?,
    @SerializedName(value="password")
    val password: String?,
    @SerializedName(value="phoneNumber")
    val phoneNumber: String?
)

class GetCourseListInCustomerCartCommand(
    val customerId: String,
)

class GetCourseGroupListInCustomerCartCommand(
    val customerId: String,
)

data class AddCourseToCustomerCartCommand(
    @SerializedName(value="courseId")
    val courseId: String?,
    val customerId: String
)

data class AddCourseGroupToCustomerCartCommand(
    @SerializedName(value="courseGroupId")
    val courseGroupId: String?,
    val customerId: String,
)

data class RemoveCourseFromCartCommand(
    @SerializedName(value="courseId")
    val courseId: String?,
    val customerId: String,
)

data class RemoveCourseGroupFromCartCommand(
    @SerializedName(value="courseGroupId")
    val courseGroupId: String?,
    val customerId: String,
)
