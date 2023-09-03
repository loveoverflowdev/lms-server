package commands

import models.products.course.Course
import java.util.*

fun CreateCourseCommand.toModel(): Course {
    // val uniqueID = UUID.randomUUID().toString()
    return Course(
        id = "",
        title = title ?: "",
        coverImage = coverImage,
        instructor = instructor ?: "",
        description = description ?: "",
        primaryCoins = primaryCoins ?: 0,
        secondaryCoins = secondaryCoins ?: 0,
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
        secondaryCoins = 0,
    );
}
