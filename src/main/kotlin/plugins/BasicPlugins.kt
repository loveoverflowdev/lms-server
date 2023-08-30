package plugins

import database.DatabaseFactory
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureBasic() {
    DatabaseFactory.init()
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            // serializeNulls()
        }
    }
}
