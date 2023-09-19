package plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import routing.customer.customerCartRoutes
import routing.general.authRoutes
import routing.general.courseGroupRoutes
import routing.general.courseRoutes

fun Application.configureRouting() {
    install(Routing) {
        route("/api/v1") {
            authRoutes()
            courseRoutes()
            courseGroupRoutes()
            customerCartRoutes()
        }
    }
}
