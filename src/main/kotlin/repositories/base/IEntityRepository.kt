package repositories.base

import kotlin.Result

interface IEntityRepository<TModel> {
    suspend fun getAll(): Result<List<TModel>>
    suspend fun findById(id: String): Result<TModel?>
    suspend fun find(predicate: (TModel) -> Boolean): Result<List<TModel>>
    suspend fun add(model: TModel): Result<TModel>
    suspend fun delete(id: String): Result<TModel?>
    suspend fun update(model: TModel): Result<TModel?>
}
