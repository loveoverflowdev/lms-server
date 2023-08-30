package repositories.cart;

import models.cart.item.CartItem
import repositories.base.BaseRepository

class CartRepository: BaseRepository<CartItem>(), ICartRepository {
    override suspend fun getAll(): Result<List<CartItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): Result<CartItem?> {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (CartItem) -> Boolean): Result<List<CartItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun add(entity: CartItem): Result<CartItem> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Result<CartItem?> {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: CartItem): Result<CartItem?> {
        TODO("Not yet implemented")
    }

}
