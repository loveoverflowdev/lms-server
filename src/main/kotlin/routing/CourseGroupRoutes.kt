package routing

import commands.CreateCourseGroupCommand
import commands.GetCourseGroupListOnTopCommand
import commands.UpdateCourseGroupCommand
import dtos.products.group.CourseGroupDTO
import dtos.response.ResponseDTO
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseGroupRoutes() {
    route("/course-groups") {
        get {
            call.respondRedirect("course-groups/top")
        }

        get("top") {
            val command = GetCourseGroupListOnTopCommand()
            val courseGroupResponse = courseGroupService.getCourseGroupListOnTop(command)
            courseGroupResponse
                .onSuccess { courseGroups ->
                    val courseGroupDto = courseGroups.map { CourseGroupDTO.of(it) }
                    call.respond(
                        ResponseDTO(
                            data = courseGroupDto,
                        )
                    )
                }
                .onFailure { throw it }
        }

        post {
            val command = call.receive<CreateCourseGroupCommand>()
            val courseGroupResponse = courseGroupService.createCourseGroup(command)
            courseGroupResponse
                .onSuccess {
                    val courseGroupDto = CourseGroupDTO.of(it)
                    call.respond(
                        ResponseDTO(
                            data = courseGroupDto
                        )
                    )
                }
                .onFailure { throw it }
        }

        put {
            val command = call.receive<UpdateCourseGroupCommand>()
            val courseGroupResponse = courseGroupService.updateCourseGroup(command)
            courseGroupResponse
                .onSuccess {
                    val courseGroupDto = CourseGroupDTO.of(it)
                    call.respond(
                        ResponseDTO(
                            data = courseGroupDto
                        )
                    )

                }
                .onFailure { throw it }
        }
    }
}
