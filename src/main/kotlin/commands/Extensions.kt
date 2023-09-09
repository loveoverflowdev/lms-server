package commands

import models.products.course.Course
import models.products.group.CourseGroup

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
