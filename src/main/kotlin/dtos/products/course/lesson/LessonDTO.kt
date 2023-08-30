package dtos.products.course.lesson

import com.google.gson.annotations.SerializedName
import dtos.products.course.base.BaseDTO
import models.products.course.lesson.Lesson

class LessonDTO(
    @SerializedName(value = "id")
    val id: String,

    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "index")
    val index: Int,

    @SerializedName(value = "content")
    val content: String
) : BaseDTO() {
    companion object {
        fun of(lesson: Lesson): LessonDTO {
            return LessonDTO(
                id = lesson.id,
                name = lesson.name,
                index = lesson.index,
                content = lesson.content
            )
        }
    }
}
