package commands

/// Course
class CreateCourseCommand(
    val title: String?,
    val coverImage: String?,
    val instructor: String?,
    val description: String?,
    val primaryCoins: Int?,
    val secondaryCoins: Int?,
    val tags: List<String>?,
)

class DeleteCourseCommand(val id: String)

class GetCourseListBySearchTextCommand(val searchText: String?)

class GetCourseListOnTopCommand()

class GetCourseByIdCommand(val id: String?)

class UpdateCourseCommand(
    val id: String,
    val title: String?,
    val coverImage: String?,
    val instructor: String?,
    val description: String?,
    val price: Double?,
    val tags: MutableList<String>? = mutableListOf()
)

/// Course Group
class GetCourseGroupListOnTopCommand()

class GetCourseGroupListBySearchTextCommand()

class UpdateCourseGroupCommand()

class CreateCourseGroupCommand()

class DeleteCourseGroupCommand()

class GetCourseGroupByIdCommand()

/// Category
class CreateCategoryCommand()

class UpdateCategoryCommand()

class GetCategoryListCommand()

class DeleteCategoryCommand()

/// Authentication
class LogInCommand()

class SignUpCommand()

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

