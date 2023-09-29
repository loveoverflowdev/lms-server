package commands

import com.google.gson.annotations.SerializedName
import models.products.course.Course
import models.products.group.CourseGroup

class SellerLogInCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value = "password")
    val password: String?,
)


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

class AddCourseToGroupCommand(
    @SerializedName(value = "id")
    val id: String?,
    @SerializedName(value = "courseGroupId")
    val courseGroupId: String?
)

fun CreateCourseCommand.toModel(): Course {
    // val uniqueID = UUID.randomUUID().toString()
    return Course(
        id = "",
        title = title ?: "",
        coverImage = coverImage,
        instructor = instructor ?: "",
        description = description ?: "",
        primaryCoins = primaryCoins ?: 0,
        secondaryCoins = secondaryCoins ?: 0
    );
}

fun UpdateCourseCommand.toModel(): Course {
    return Course(
        id = id,
        title = title ?: "",
        coverImage = coverImage ?: "",
        instructor = instructor ?: "",
        description = description ?: "",
        primaryCoins = 0,
        secondaryCoins = 0
    );
}

fun UpdateCourseGroupCommand.toModel(): CourseGroup {
    return CourseGroup(
        id = id,
        title = title ?: "",
        coverImage = coverImage ?: "",
        description = description ?: "",
        primaryCoins = 0,
        secondaryCoins = 0
    );
}

fun CreateCourseGroupCommand.toModel() : CourseGroup {
    return CourseGroup(
        id = id,
        title = title ?: "",
        coverImage = coverImage ?: "",
        description = description ?: "",
        primaryCoins = 0,
        secondaryCoins = 0
    );
}
