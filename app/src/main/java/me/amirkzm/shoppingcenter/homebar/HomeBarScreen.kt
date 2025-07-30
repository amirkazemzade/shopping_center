package me.amirkzm.shoppingcenter.homebar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.cart.ui.CartRoute
import me.amirkzm.shoppingcenter.product.productslist.presentation.ProductsListRoute

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
@Destination<RootGraph>(start = true)
fun HomeBarScreen(
    navigator: DestinationsNavigator,
) {

    val homeBarItemsList = listOf(
        HomeBarItem(
            title = "Home",
            icon = ImageVector.vectorResource(R.drawable.round_home_24),
        ),
        HomeBarItem(
            title = "Cart",
            icon = ImageVector.vectorResource(R.drawable.shopping_basket_24),
        ),
    )

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                homeBarItemsList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavigationIndex.intValue == index,
                        onClick = {
                            selectedNavigationIndex.intValue = index
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
            .exclude(TopAppBarDefaults.windowInsets)
    ) { outerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(outerPadding)
        ) {
            val contentWindowInsets = ScaffoldDefaults.contentWindowInsets
                .exclude(BottomAppBarDefaults.windowInsets)
                .exclude(WindowInsets.navigationBars)

            if (selectedNavigationIndex.intValue == 0) {
                ProductsListRoute(
                    navigator = navigator,
                    contentWindowInsets = contentWindowInsets
                )
            } else {
                CartRoute(
                    navigator = navigator,
                    contentWindowInsets = contentWindowInsets
                )
            }
        }
    }
}
