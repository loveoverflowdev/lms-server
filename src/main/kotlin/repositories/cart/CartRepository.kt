package repositories.cart;

import models.products.base.Product
import repositories.base.BaseRepository

class CartRepository: BaseRepository<Product>(), ICartRepository {
    override suspend fun getAll(): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): Result<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (Product) -> Boolean): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun add(entity: Product): Result<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Result<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: Product): Result<Product?> {
        TODO("Not yet implemented")
    }

}
