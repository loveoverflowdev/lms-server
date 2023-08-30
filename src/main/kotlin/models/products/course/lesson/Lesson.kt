package models.products.course.lesson

import models.base.Model
import models.products.course.Course

class Lesson(
    id: String,
    val name: String,
    val index: Int,
    val content: String,
) : Model(id)
