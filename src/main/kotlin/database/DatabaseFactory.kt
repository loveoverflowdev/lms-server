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
//        val dbHost = System.getenv("DB_HOST")
//        val dbPort = System.getenv("DB_PORT")
//        val dbUser = System.getenv("DB_USER")
//        val dbPassword = System.getenv("DB_PASSWORD")
//        val dbName = System.getenv("DB_NAME")

        val jdbcUrl = System.getenv("JDBC_URL") ?: throw IllegalStateException("Missing JDBC_URL in environment variables")
        val config = HikariConfig()
        config.jdbcUrl = jdbcUrl
        // config.jdbcUrl = "jdbc:mysql://127.0.0.1:3307/lms?user=root&password=root"

        // Other HikariCP configuration options
        config.maximumPoolSize = 10
        config.connectionTimeout = 300
        config.idleTimeout = 600000
        config.driverClassName = "com.mysql.cj.jdbc.Driver"

        val dataSource = HikariDataSource(config)
        database = Database.connect(dataSource)
    }
}
