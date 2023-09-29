package database.schemas.base

import kotlinx.coroutines.Dispatchers
import models.base.Model
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

abstract class BaseEntity(
    open val id: String
) {
    abstract fun toModel(): Model
}

abstract class BaseTable(
    name: String
) : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = varchar("id", 50)
        .clientDefault { UUID.randomUUID().toString() }
        .uniqueIndex()
        .entityId()
}

abstract class BaseSchema<TTable: Table, TEntity>(
    database: Database,
) {
    init {
        transaction(database) {
            SchemaUtils.create<TTable>()
        }
    }

    protected abstract suspend fun create(entity: TEntity): TEntity?
    protected abstract suspend fun read(id: String): TEntity?

    protected abstract suspend fun update(id: String, entity: TEntity): TEntity?
    protected abstract suspend fun delete(id: String): TEntity?

    protected suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
