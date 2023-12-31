package plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configWebApp() {
    routing {
        singlePageApplication {
            useResources = true
            filesPath = "web"
            defaultPage = "index.html"
            ignoreFiles { it.endsWith(".txt") }
        }
    }
}
