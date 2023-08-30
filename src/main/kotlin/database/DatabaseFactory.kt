package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object DatabaseFactory {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun init() {
        initDB()
        transaction {
            addLogger(StdOutSqlLogger)
        }
    }

    private fun initDB() {
        val config = HikariConfig("/hikari.properties")
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }
}
