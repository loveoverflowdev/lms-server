package routing.general

import commands.CreateCourseGroupCommand
import commands.GetCourseGroupByIdCommand
import commands.GetCourseGroupListOnTopCommand
import commands.UpdateCourseGroupCommand
import dtos.products.group.CourseGroupDTO
import dtos.response.ResponseDTO
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import repositories.products.group.CourseGroupRepository
import repositories.products.group.ICourseGroupRepository
import services.products.group.CourseGroupService
import services.products.group.ICourseGroupService

val courseGroupRepository: ICourseGroupRepository = CourseGroupRepository()
val courseGroupService: ICourseGroupService = CourseGroupService(courseGroupRepository)

fun Route.courseGroupRoutes() {
    route("/course-groups") {
        get {
            call.respondRedirect("course-groups/top")
        }

        get("top") {
            val command = GetCourseGroupListOnTopCommand()
            courseGroupService.getCourseGroupListOnTop(command)
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
    }

    route("/course-group") {
        get("{id}"){
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                throw BadRequestException("Missing [id] for course group deleting")
            }
            val command = GetCourseGroupByIdCommand(id)
            courseGroupService.getCourseGroupById(command)


        }

        post {
            val command = call.receive<CreateCourseGroupCommand>()
            courseGroupService.createCourseGroup(command)
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
            courseGroupService.updateCourseGroup(command)
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

        delete("{id}") {
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                throw BadRequestException("Missing [id] for course group deleting")
            }
            val command = call
        }
    }
}
