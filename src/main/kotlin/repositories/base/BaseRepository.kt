package repositories.base

import models.base.Model

open class BaseRepository<TModel>: IEntityRepository<TModel> where TModel: Model {
    private var models: MutableList<TModel> = mutableListOf()

    override suspend fun getAll(): Result<List<TModel>> {
        return Result.success(models)
    }

    override suspend fun findById(id: String): Result<TModel?> {
        val model = models.find {it.id == id}
        return Result.success(model)
    }

    override suspend fun find(predicate: (TModel) -> Boolean): Result<List<TModel>> {
        val filteredModels =  models.filter(predicate)
        return Result.success(filteredModels)
    }

    override suspend fun delete(id: String): Result<TModel?> {
        val index = models.indexOfFirst { it.id == id }
        if (index < 0 ) {
            return Result.success(null)
        }
        return Result.success(models.removeAt(index))
    }

    override suspend fun update(id: String, model: TModel): Result<TModel?> {
        val index = models.indexOfFirst { it.id == model.id }
        if (index < 0) {
            return  Result.success(null)
        }
        models[index] = model;
        return Result.success(models[index])
    }

    override suspend fun add(model: TModel): Result<TModel> {
        models.add(model)
        return Result.success(model)
    }
}
