import com.typesafe.config.ConfigFactory
import database.DatabaseFactory
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import plugins.*
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
            configureRequestValidation()
            configureAuthentication()
            configureStatusPage()
            configureRouting()
            configWebApp()
        }
        connector {
            host = config.property("ktor.deployment.host").getString()
            port = config.property("ktor.deployment.port").getString().toInt()
        }
    }

    embeddedServer(Netty, appEngineEnv).start(wait = true)
}
