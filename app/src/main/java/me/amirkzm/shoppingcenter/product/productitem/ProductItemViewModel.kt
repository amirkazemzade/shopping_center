package me.amirkzm.shoppingcenter.product.productitem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.cart.domain.usecase.AddItemToCartUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.DecreaseCartItemQuantityUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.GetCartUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.IncreaseCartItemQuantityUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.RemoveItemFromCartUseCase
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.domain.models.isSuccess
import me.amirkzm.shoppingcenter.common.domain.models.mapData
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrNull
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrThrow
import me.amirkzm.shoppingcenter.product.common.domain.usecases.GetProductItemUseCase
import javax.inject.Inject

@HiltViewModel
class ProductItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductItemUseCase: GetProductItemUseCase,
    getCartUseCase: GetCartUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val decreaseQuantityInCartUseCase: DecreaseCartItemQuantityUseCase,
    private val increaseQuantityInCartUseCase: IncreaseCartItemQuantityUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
) : ViewModel() {

    private var fetchProductJob: Job? = null
    private val _productStateFlow: MutableStateFlow<ProductItemState> =
        MutableStateFlow(RequestState.Idle)

    val productStateFlow: StateFlow<ProductItemState> = _productStateFlow.asStateFlow()


    val cartStateFlow: StateFlow<RequestState<CartItemModel?>> =
        combine(
            flow = productStateFlow,
            flow2 = getCartUseCase()
        ) { productState, cartState ->
            if (!productState.isSuccess) RequestState.Idle
            cartState.mapData { cartItems ->
                cartItems.find { it.id == productState.successDataOrNull?.id }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            RequestState.Idle
        )

    private var addToCartJob: Job? = null
    private var increaseQuantityJob: Job? = null
    private var decreaseQuantityJob: Job? = null
    private var removeFromCartJob: Job? = null

    private var _addToCartState = MutableStateFlow<RequestState<Unit>>(RequestState.Idle)
    private var _increaseQuantityState = MutableStateFlow<RequestState<Unit>>(RequestState.Idle)
    private var _decreaseQuantityState = MutableStateFlow<RequestState<Unit>>(RequestState.Idle)
    private var _removeFromCartState = MutableStateFlow<RequestState<Unit>>(RequestState.Idle)

    val addToCartState = _addToCartState.asStateFlow()
    val increaseQuantityState = _increaseQuantityState.asStateFlow()
    val decreaseQuantityState = _decreaseQuantityState.asStateFlow()
    val removeFromCartState = _removeFromCartState.asStateFlow()


    fun fetchProduct(id: Int) {
        viewModelScope.launch {
            fetchProductJob?.cancelAndJoin()
            fetchProductJob = launch {
                getProductItemUseCase(id).collect { newState ->
                    _productStateFlow.update { state -> newState }
                }
            }
        }
    }

    fun addToCart(productId: Int) {
        if (cartStateFlow.value.isSuccess && cartStateFlow.value.successDataOrThrow?.id == productId) return
        viewModelScope.launch {
            // Cancel any previous add to cart operation
            addToCartJob?.cancelAndJoin()

            // Start a new add to cart operation
            addToCartJob = launch {
                addItemToCartUseCase(
                    cartItemModel = CartItemModel(
                        id = productId,
                        quantity = 1
                    )
                ).collect { newState ->
                    _addToCartState.update { newState }
                }
            }
        }
    }

    fun increaseQuantityInCart(productId: Int) {
        if (cartStateFlow.value.isSuccess && cartStateFlow.value.successDataOrThrow == null) return
        viewModelScope.launch {
            increaseQuantityJob?.cancelAndJoin()

            increaseQuantityJob = launch {
                increaseQuantityInCartUseCase(
                    id = productId,
                ).collect { newState ->
                    _increaseQuantityState.update { newState }
                }
            }

        }
    }

    fun decreaseQuantityInCart(productId: Int) {
        if (cartStateFlow.value.isSuccess && cartStateFlow.value.successDataOrThrow == null) return
        viewModelScope.launch {
            decreaseQuantityJob?.cancelAndJoin()
            decreaseQuantityJob = launch {
                decreaseQuantityInCartUseCase(
                    id = productId,
                ).collect { newState ->
                    _decreaseQuantityState.update { newState }
                }
            }

        }
    }

    fun removeFromCart(productId: Int) {
        if (cartStateFlow.value.isSuccess && cartStateFlow.value.successDataOrThrow == null) return
        viewModelScope.launch {
            removeFromCartJob?.cancelAndJoin()
            removeFromCartJob = launch {
                removeItemFromCartUseCase(
                    id = productId,
                ).collect { newState ->
                    _removeFromCartState.update { newState }
                }
            }
        }
    }
}