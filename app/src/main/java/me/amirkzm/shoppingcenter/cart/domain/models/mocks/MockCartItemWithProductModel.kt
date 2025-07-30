package me.amirkzm.shoppingcenter.cart.domain.models.mocks

import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating

val mockCartItemWithProductModelList = listOf(
    CartItemWithProductModel(
        product = ProductItemModel(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(
                count = 120, rate = 3.9
            )
        ),
        quantity = 1,
    ),
    CartItemWithProductModel(
        product = ProductItemModel(
            id = 2,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(
                count = 120, rate = 3.9
            )
        ),
        quantity = 1,
    ),
    CartItemWithProductModel(
        product = ProductItemModel(
            id = 3,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(
                count = 120, rate = 3.9
            )
        ),
        quantity = 3,
    )
)

val mockCartItemWithNullProductModelList = listOf(
    CartItemWithProductModel(
        product = null,
        quantity = 1,
    ),
    CartItemWithProductModel(
        product = null,
        quantity = 1,
    ),
    CartItemWithProductModel(
        product = null,
        quantity = 3,
    )
)
