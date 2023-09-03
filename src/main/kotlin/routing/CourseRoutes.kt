package routing

import commands.CreateCourseCommand
import commands.GetCourseByIdCommand
import io.ktor.server.application.*
import io.ktor.server.routing.*

import commands.GetCourseListOnTopCommand
import dtos.products.course.CourseDTO
import dtos.response.ResponseDTO
import dtos.response.StatusDTO
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
            val coursesResponse = courseService.getCourseListOnTop(command)
            coursesResponse.onSuccess { courses ->
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
            val coursesResponse = courseService.getCourseById(command)
            coursesResponse.onSuccess { course ->
                val courseDto = if(course == null) null else CourseDTO.of(course)
                call.respond(ResponseDTO(
                    data = courseDto,
                    status = StatusDTO(code = 200)
                ))
            }.onFailure { throw it }
        }

        post {
            val command = call.receive<CreateCourseCommand>()
            val courseResponse = courseService.createCourse(command)
            courseResponse.onSuccess { course ->
                val courseDto = CourseDTO.of(course)
                call.respond(ResponseDTO(
                    data = courseDto,
                    status = StatusDTO(code = 200)
                ))
            }.onFailure { throw it }
        }
    }
}
