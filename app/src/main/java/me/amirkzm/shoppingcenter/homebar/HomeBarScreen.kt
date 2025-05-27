package me.amirkzm.shoppingcenter.homebar

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.cart.CartRoute
import me.amirkzm.shoppingcenter.product.productslist.presentation.ProductsListRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        }
    ) {
        if (selectedNavigationIndex.intValue == 0) {
            ProductsListRoute(
                navigator = navigator,
            )
        } else {
            CartRoute()
        }
    }
}
