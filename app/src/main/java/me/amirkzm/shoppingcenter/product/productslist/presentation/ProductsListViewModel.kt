package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.usecases.GetProductsListUseCase

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductsListUseCase: GetProductsListUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProductsListState> =
        MutableStateFlow(RequestState.Idle)

    val stateFlow: StateFlow<ProductsListState> = _stateFlow.asStateFlow()

    fun getProductsList() {
        viewModelScope.launch {
            getProductsListUseCase().collect { newState ->
                _stateFlow.update { state -> newState }
            }
        }
    }
}