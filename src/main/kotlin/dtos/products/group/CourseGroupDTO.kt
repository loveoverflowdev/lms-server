package dtos.products.group

import com.google.gson.annotations.SerializedName
import dtos.products.course.base.BaseDTO
import models.products.group.CourseGroup

class CourseGroupDTO(
    @SerializedName(value = "id")
    val id: String,

    @SerializedName(value = "title")
    val title: String,

    @SerializedName(value = "description")
    val description: String,

    @SerializedName(value = "coverImage")
    val coverImage: String?,

    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int,

    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?
) : BaseDTO() {
    companion object {
        fun of(courseGroup: CourseGroup): CourseGroupDTO {
            return CourseGroupDTO(
                id = courseGroup.id,
                title = courseGroup.title,
                coverImage = courseGroup.coverImage,
                description = courseGroup.description,
                primaryCoins = courseGroup.primaryCoins,
                secondaryCoins = courseGroup.secondaryCoins,
            )
        }
    }
}
