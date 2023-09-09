package routing

import commands.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

import dtos.products.course.CourseDTO
import dtos.response.ResponseDTO
import dtos.response.StatusDTO
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import repositories.products.course.CourseRepository
import repositories.products.course.ICourseRepository
import repositories.products.group.CourseGroupRepository
import repositories.products.group.ICourseGroupRepository

import services.products.course.CourseService
import services.products.course.ICourseService
import services.products.group.CourseGroupService
import services.products.group.ICourseGroupService

val courseRepository: ICourseRepository = CourseRepository()
val courseService: ICourseService = CourseService(courseRepository)

val courseGroupRepository: ICourseGroupRepository = CourseGroupRepository()
val courseGroupService: ICourseGroupService = CourseGroupService(courseGroupRepository)

fun Route.courseRoutes() {
    route("/courses") {
        get {
            call.respondRedirect("courses/top")
        }
        get("top") {
            val command = GetCourseListOnTopCommand()
            courseService.getCourseListOnTop(command)
                .onSuccess { courses ->
                    val coursesDto = courses.map { CourseDTO.of(it) }
                    call.respond(ResponseDTO(
                        data = coursesDto,
                    ))
                }.onFailure { throw it }
        }
    }

    route("/course") {
        get("{id}") {
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                throw BadRequestException("Missing [id] for course detail request")
            }
            val command = GetCourseByIdCommand(id)
            courseService.getCourseById(command)
                .onSuccess {
                    val courseDto = if(it == null) null else CourseDTO.of(it)
                    call.respond(ResponseDTO(
                        data = courseDto,
                    ))
                }
                .onFailure { throw it }
        }

        post {
            val command = call.receive<CreateCourseCommand>()
            courseService.createCourse(command)
                .onSuccess {
                    val courseDto = CourseDTO.of(it)
                    call.respond(ResponseDTO(
                        data = courseDto,
                    ))
                }
                .onFailure { throw it }
        }

        put {
            val command = call.receive<UpdateCourseCommand>()
            courseService.updateCourse(command)
                .onSuccess {
                    val courseDto = CourseDTO.of(it)
                    call.respond(ResponseDTO(
                        data = courseDto,
                    ))
                }
                .onFailure { throw it }
        }

        delete {
            val command = call.receive<DeleteCourseCommand>()
            courseService.deleteCourse(command)
                .onSuccess {
                    val courseDto = CourseDTO.of(it)
                    call.respond(ResponseDTO(
                        data = courseDto,
                    ))
                }
                .onFailure { throw it }

        }
    }
}
