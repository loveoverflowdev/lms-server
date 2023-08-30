package repositories.cart

import models.cart.item.CartItem
import repositories.base.IEntityRepository

interface ICartRepository: IEntityRepository<CartItem> {

}
