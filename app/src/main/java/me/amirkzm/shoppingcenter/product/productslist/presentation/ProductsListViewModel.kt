package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.usecases.GetCategoriesUseCase
import me.amirkzm.shoppingcenter.product.common.domain.usecases.GetProductsListUseCase

@HiltViewModel(assistedFactory = ProductsListViewModel.ProductListViewModelFactory::class)
class ProductsListViewModel @AssistedInject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductsListUseCase: GetProductsListUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    @Assisted startingSelectedCategory: Category? = null,
) : ViewModel() {

    @dagger.assisted.AssistedFactory
    interface ProductListViewModelFactory {
        fun create(startingSelectedCategory: Category? = null): ProductsListViewModel
    }

    private val _stateFlow: MutableStateFlow<ProductsListState> =
        MutableStateFlow(RequestState.Idle)

    val stateFlow: StateFlow<ProductsListState> = _stateFlow.asStateFlow()

    private val _categoriesFlow: MutableStateFlow<RequestState<List<Category>>> =
        MutableStateFlow(RequestState.Idle)

    val categoriesFlow: StateFlow<RequestState<List<Category>>> = _categoriesFlow.asStateFlow()

    private val _selectedCategory: MutableStateFlow<Category?> =
        MutableStateFlow(startingSelectedCategory)

    val selectedCategory: StateFlow<Category?> = _selectedCategory.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getProductsList() {
        viewModelScope.launch {
            _selectedCategory.flatMapLatest { category ->
                getProductsListUseCase(category = category)
            }.collect { newState ->
                _stateFlow.value = newState
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { newState ->
                _categoriesFlow.update { state -> newState }
            }
        }
    }

    fun selectCategory(newCategory: Category) {
        _selectedCategory.update { category -> newCategory }
    }

    fun deselectCategory() {
        _selectedCategory.update { category -> null }
    }
}