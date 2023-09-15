package plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import routing.*

fun Application.configureRouting() {
    install(Routing) {
        route("/api/v1") {
            authRoutes()
            courseRoutes()
            courseGroupRoutes()
            cartRoutes()
        }
    }
}
