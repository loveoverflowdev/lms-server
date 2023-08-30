package dtos.products.group

class CourseGroupDTO(
    val id: String,
    val name: String,
    val description: String,
    val coverImage: String,
    val primaryCoins: Double,
    val secondaryCoins: Double?
)
