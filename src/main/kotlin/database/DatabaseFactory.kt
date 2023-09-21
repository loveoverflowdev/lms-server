package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

object DatabaseFactory {
    private var database: Database? = null

    val databaseShared: Database
        get() = database ?: throw SQLException("Unable to establish a connection to the database")

    fun init() {
        initDB()
        transaction {
            addLogger(StdOutSqlLogger)
        }
    }

    private fun initDB() {
        val dbHost = System.getenv("DB_HOST")

        val dbPort = System.getenv("DB_PORT")
        val dbUser = System.getenv("DB_USER")
        val dbPassword = System.getenv("DB_PASSWORD")
        val dbName = System.getenv("DB_NAME")

        // Check if any of the required environment variables is missing
        if (dbPort == null || dbUser == null || dbPassword == null) {
            throw IllegalStateException("Missing one or more DB environment variables")
        }

        // Create a HikariCP configuration using the dynamic properties
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://${dbHost}:${dbPort}/${dbName}?user=${dbUser}&password=${dbPassword}"

        // Other HikariCP configuration options
        config.maximumPoolSize = 10

        val dataSource = HikariDataSource(config)
        database = Database.connect(dataSource)
    }
}
