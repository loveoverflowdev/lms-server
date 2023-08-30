import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import plugins.configureBasic
import plugins.configureRequestValidation
import plugins.configureRouting
import plugins.configureStatusPage

fun main() {
    embeddedServer(
        Netty, port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    configureBasic()
    configureRouting()
    configureStatusPage()
    configureRequestValidation()
}
