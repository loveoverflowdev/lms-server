package dtos.products.course

import com.google.gson.annotations.SerializedName
import dtos.products.course.base.BaseDTO
import models.products.course.Course

data class CourseDTO(
    @SerializedName(value = "id")
    val id: String,

    @SerializedName(value = "title")
    val title: String,

    @SerializedName(value = "instructor")
    val instructor: String,

    @SerializedName(value = "coverImage")
    val coverImage: String?,

    @SerializedName(value = "description")
    val description: String,

    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int,

    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?
) : BaseDTO() {
    companion object {
        fun of(course: Course): CourseDTO {
            return CourseDTO(
                id = course.id,
                title = course.title,
                instructor = course.instructor,
                coverImage = course.coverImage,
                description = course.description,
                primaryCoins = course.primaryCoins,
                secondaryCoins = course.secondaryCoins,
            )
        }
    }
}
