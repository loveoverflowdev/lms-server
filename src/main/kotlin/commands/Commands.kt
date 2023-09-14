package commands

import com.google.gson.annotations.SerializedName

/// Course
class CreateCourseCommand(
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "instructor")
    val instructor: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?,
    val tags: List<String>?,
)

class UpdateCourseCommand(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "instructor")
    val instructor: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?,
    val tags: MutableList<String>? = mutableListOf()
)

class DeleteCourseCommand(val id: String)

class GetCourseListBySearchTextCommand(val searchText: String?)

class GetCourseListOnTopCommand()

class GetCourseByIdCommand(val id: String?)

/// Course Group
class GetCourseGroupListOnTopCommand()

class GetCourseGroupListBySearchTextCommand()

class UpdateCourseGroupCommand(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?,
)

class CreateCourseGroupCommand(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?
)

class DeleteCourseGroupCommand(
    val id: String?
)

class GetCourseGroupByIdCommand(
    val id: String?
)

class AddCourseToGroupCommand(
    @SerializedName(value = "id")
    val id: String?,
    @SerializedName(value = "courseGroupId")
    val courseGroupId: String?
)

/// Category
class CreateCategoryCommand()

class UpdateCategoryCommand()

class GetCategoryListCommand()

class DeleteCategoryCommand()

/// Authentication
class CustomerLogInCommand(
    @SerializedName(value = "usernameOrEmail")
    val usernameOrEmail: String?,
    @SerializedName(value = "password")
    val password: String?,
)

class AdminLogInCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value = "password")
    val password: String?,
)

class SellerLogInCommand(
    @SerializedName(value = "username")
    val username: String?,
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

class AddCourseToCustomerCartCommand(
    val courseId: String,
    val customerId: String
)

class AddCourseGroupToCustomerCartCommand(
    val courseGroupId: String,
    val customerGroupId: String,
)

class AdminGrantCoinsToCustomerCommand(

)

class LogOutCommand()

/// User
class ChangePassword()

class UpdateProfile()

class GetProfile()

/// Cart
class AddProductToCart()

class RemoteProductFromCart()

class ClearCart()

/// Coin
class BuyCoins()

/// Enrollment
class EnrollCourse()

class EnrollCourseGroup()
