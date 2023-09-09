package plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import routing.courseGroupRoutes
import routing.courseRoutes

fun Application.configureRouting() {
    install(Routing) {
        route("/api/v1") {
            courseRoutes()
            courseGroupRoutes()
        }
    }
}
