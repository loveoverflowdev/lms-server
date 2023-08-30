package repositories.base

import models.base.Model

open class BaseRepository<TModel>: IEntityRepository<TModel> where TModel: Model {
    private var entities: MutableList<TModel> = mutableListOf()

    override suspend fun getAll(): Result<List<TModel>> {
        return Result.success(entities)
    }

    override suspend fun findById(id: String): Result<TModel?> {
        val entity = entities.find {it.id == id}
        return Result.success(entity)
    }

    override suspend fun find(predicate: (TModel) -> Boolean): Result<List<TModel>> {
        val filteredEntities =  entities.filter(predicate)
        return Result.success(filteredEntities)
    }

    override suspend fun delete(id: String): Result<TModel?> {
        val index = entities.indexOfFirst { it.id == id }
        if (index < 0 ) {
            return Result.success(null)
        }
        return Result.success(entities.removeAt(index))
    }

    override suspend fun update(item: TModel): Result<TModel?> {
        val index = entities.indexOfFirst { it.id == item.id }
        if (index < 0) {
            return  Result.success(null)
        }
        entities[index] = item;
        return Result.success(entities[index])
    }

    override suspend fun add(entity: TModel): Result<TModel> {
        entities.add(entity)
        return Result.success(entity)
    }
}
