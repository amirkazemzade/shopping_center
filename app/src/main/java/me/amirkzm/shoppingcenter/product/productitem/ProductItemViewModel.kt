package me.amirkzm.shoppingcenter.product.productitem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.usecases.GetProductItemUseCase
import javax.inject.Inject

@HiltViewModel
class ProductItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductItemUseCase: GetProductItemUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProductItemState> =
        MutableStateFlow(RequestState.Idle)

    val stateFlow: StateFlow<ProductItemState> = _stateFlow.asStateFlow()

    fun fetchProduct(id: Int) {
        viewModelScope.launch {
            getProductItemUseCase(id).collect { newState ->
                _stateFlow.update { state -> newState }
            }
        }
    }
}