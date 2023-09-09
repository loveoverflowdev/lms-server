package routing

import commands.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

import dtos.products.course.CourseDTO
import dtos.response.ResponseDTO
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import repositories.products.course.CourseRepository
import repositories.products.course.ICourseRepository

import services.products.course.CourseService
import services.products.course.ICourseService

val courseRepository: ICourseRepository = CourseRepository()
val courseService: ICourseService = CourseService(courseRepository)

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

        delete("{id}") {
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                throw BadRequestException("Missing [id] for course deleting")
            }
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
