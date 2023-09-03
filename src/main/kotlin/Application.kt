import com.typesafe.config.ConfigFactory
import database.DatabaseFactory
import database.schemas.products.course.CourseEntity
import database.schemas.products.course.CourseSchema
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import plugins.configureBasic
import plugins.configureRequestValidation
import plugins.configureRouting
import plugins.configureStatusPage

fun main() {
    val configName = "application.conf"
    val appEngineEnv = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load(configName))
        log = LoggerFactory.getLogger("ktor.application")
        developmentMode = false
        module {
            DatabaseFactory.init()
            configureBasic()
            configureStatusPage()
            configureRouting()
        }
        connector {
            host = config.property("ktor.deployment.host").getString()
            port = config.property("ktor.deployment.port").getString().toInt()
        }
    }

    embeddedServer(Netty, appEngineEnv).start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    configureBasic()
    configureRouting()
    configureStatusPage()
    configureRequestValidation()
}
