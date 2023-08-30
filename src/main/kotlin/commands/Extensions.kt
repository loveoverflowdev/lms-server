package commands

import models.products.course.Course
import java.util.*

fun CreateCourseCommand.toEntity(): Course {
    val uniqueID = UUID.randomUUID().toString()
    return Course(
        id = uniqueID,
        title = title ?: "",
        coverImage = coverImage ?: "",
        instructor = instructor ?: "",
        description = description ?: "",
        primaryCoins = 0.0,
        secondaryCoins = 0.0,
    );
}

fun UpdateCourseCommand.toEntity(): Course {
    return Course(
        id = id,
        title = title ?: "",
        coverImage = coverImage ?: "",
        instructor = instructor ?: "",
        description = description ?: "",
        primaryCoins = 0.0,
        secondaryCoins = 0.0,
    );
}
