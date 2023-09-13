package repositories.cart;

import models.products.base.Product

class CartRepository: ICartRepository {
    override suspend fun getAll(): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): Result<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (Product) -> Boolean): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: Product): Result<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Result<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, model: Product): Result<Product> {
        TODO("Not yet implemented")
    }

}
