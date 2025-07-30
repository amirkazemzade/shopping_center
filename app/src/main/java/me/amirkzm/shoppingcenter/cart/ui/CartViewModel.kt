package me.amirkzm.shoppingcenter.cart.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.cart.domain.usecase.DecreaseCartItemQuantityUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.GetCartWithProductsUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.IncreaseCartItemQuantityUseCase
import me.amirkzm.shoppingcenter.cart.domain.usecase.RemoveItemFromCartUseCase
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCartWithProductsUseCase: GetCartWithProductsUseCase,
    private val increaseItemQuantityUseCase: IncreaseCartItemQuantityUseCase,
    private val decreaseItemQuantityUseCase: DecreaseCartItemQuantityUseCase,
    private val removeItemUseCase: RemoveItemFromCartUseCase,
) : ViewModel() {

    // Trigger for refresh actions
    // We use a SharedFlow for events like refresh, as they are "fire and forget"
    // and we don't necessarily need to hold a "state" for the refresh itself.
    private val _refreshTrigger = MutableSharedFlow<Unit>(replay = 1)
    private val refreshTrigger = _refreshTrigger.asSharedFlow()

    fun onRefresh() {
        viewModelScope.launch {
            // Emitting a new value to the trigger.
            // Using tryEmit here for simplicity, as we don't need to suspend.
            // If you had complex logic before refreshing, you might use emit.
            _refreshTrigger.tryEmit(Unit)
        }
    }

    fun increaseItemQuantity(id: Int) {
        viewModelScope.launch {
            increaseItemQuantityUseCase(id).collect { }
        }
    }

    fun decreaseItemQuantity(id: Int) {
        viewModelScope.launch {
            decreaseItemQuantityUseCase(id).collect { }
        }
    }

    fun removeItem(id: Int) {
        viewModelScope.launch {
            removeItemUseCase(id).collect { }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val stateFlow: StateFlow<RequestState<List<CartItemWithProductModel>>> =
        refreshTrigger
            .onStart { emit(Unit) } // Emit once immediately to load initial data
            .flatMapLatest { // When refreshTrigger emits, cancel previous fetch and start a new one
                getCartWithProductsUseCase()
                    .onStart { emit(RequestState.Loading) } // Emit Loading before starting the fetch
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = RequestState.Idle // Or RequestState.Loading if you prefer
            )
}