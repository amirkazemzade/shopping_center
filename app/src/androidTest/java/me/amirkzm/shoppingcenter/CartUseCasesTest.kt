package me.amirkzm.shoppingcenter

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.cart.domain.usecase.AddItemToCartUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.ClearCartUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.DecreaseCartItemQuantityUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.GetCartUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.IncreaseCartItemQuantityUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.RemoveItemFromCartUseCase
import me.amirkzm.shoppingcenter.common.domain.models.isError
import me.amirkzm.shoppingcenter.common.domain.models.isSuccess
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrThrow
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Ensures tests run in a defined order
class CartUseCasesTest {

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Inject
    lateinit var getCartUseCase: GetCartUseCase

    @Inject
    lateinit var addItemToCartUseCase: AddItemToCartUseCase

    @Inject
    lateinit var decreaseCartItemQuantityUseCase: DecreaseCartItemQuantityUseCase

    @Inject
    lateinit var increaseCartItemQuantityUseCase: IncreaseCartItemQuantityUseCase

    @Inject
    lateinit var removeItemFromCartUseCase: RemoveItemFromCartUseCase

    @Inject
    lateinit var clearCartUseCase: ClearCartUseCase


    @Before
    fun setup() {
        rule.inject()
    }

    val cartItem1 = CartItemModel(1, 1)
    val cartItem2 = CartItemModel(2, 2)
    val cartItem3 = CartItemModel(3, 3)

    @Test
    fun test01_initialCartIsEmpty() = runTest {
        val cartFlow = getCartUseCase()
        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result.isSuccess)
        assert(result.successDataOrThrow.isEmpty())
    }

    @Test
    fun test02_addItemToCart() = runTest {
        val cartFlow = getCartUseCase()

        addItemToCartUseCase(cartItem1).collect {}

        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result.isSuccess)
        assert(result.successDataOrThrow.size == 1)
        assert(result.successDataOrThrow.contains(cartItem1))
    }

    @Test
    fun test03_addMultipleItemsToCart() = runTest {
        val cartFlow = getCartUseCase()

        // Assuming cartItem1 is already added from the previous test
        addItemToCartUseCase(cartItem2).collect {}

        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result.isSuccess)
        assert(result.successDataOrThrow.size == 2)
        assert(result.successDataOrThrow.contains(cartItem1))
        assert(result.successDataOrThrow.contains(cartItem2))
    }

    @Test
    fun test04_addMoreItemsToCart() = runTest {
        val cartFlow = getCartUseCase()

        // Assuming cartItem1 and cartItem2 are already added
        addItemToCartUseCase(cartItem3).collect {}

        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result.isSuccess)
        assert(result.successDataOrThrow.size == 3)
        assert(result.successDataOrThrow.contains(cartItem1))
        assert(result.successDataOrThrow.contains(cartItem2))
        assert(result.successDataOrThrow.contains(cartItem3))
    }

    @Test
    fun test05_increaseCartItemQuantity() = runTest {
        val cartFlow = getCartUseCase()
        // Assuming cartItem1, cartItem2, and cartItem3 are present
        // And cartItem1 has quantity
        increaseCartItemQuantityUseCase(cartItem3.id).collect {}
        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result.isSuccess)
        assert(result.successDataOrThrow.size == 3)
        assert(result.successDataOrThrow.find { it.id == cartItem3.id }!!.quantity == (cartItem3.quantity + 1))
    }

    @Test
    fun test06_decreaseCartItemQuantity() = runTest {
        val cartFlow = getCartUseCase()
        // Assuming cartItem1, cartItem2, and cartItem3 are present
        // And cartItem1 has quantity 1 from its definition
        decreaseCartItemQuantityUseCase(cartItem1.id).collect {}
        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }

        assert(result.isSuccess)
        // After decreasing cartItem1 (quantity 1), it should be removed.
        assert(result.successDataOrThrow.size == 2)
        assert(result.successDataOrThrow.contains(cartItem1).not())

        decreaseCartItemQuantityUseCase(cartItem2.id).collect {}
        val result2 = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result2.isSuccess)
        assert(result2.successDataOrThrow.size == 2)
        assert(result2.successDataOrThrow.find { it.id == cartItem2.id }!!.quantity == (cartItem2.quantity - 1))
    }

    @Test
    fun test07_clearCart() = runTest {
        val cartFlow = getCartUseCase()
        // Assuming cartItem2 and cartItem3 are present
        clearCartUseCase().collect {}
        val result = cartFlow
            .onEach { assert(it.isError.not()) }
            .first { it.isSuccess }
        assert(result.isSuccess)
        assert(result.successDataOrThrow.isEmpty())
    }
}