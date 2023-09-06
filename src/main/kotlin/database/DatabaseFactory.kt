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
        val config = HikariConfig("/hikari.properties")
        val dataSource = HikariDataSource(config)
        database = Database.connect(dataSource)
    }
}
