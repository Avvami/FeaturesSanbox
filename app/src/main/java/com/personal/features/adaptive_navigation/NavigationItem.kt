package com.personal.features.adaptive_navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.personal.features.R

sealed class NavigationItem(
    @StringRes val label: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    data object Home: NavigationItem(
        R.string.home, Icons.Filled.Home, Icons.Outlined.Home, RootNavGraph.HOME
    )

    data object Favorites: NavigationItem(
        R.string.favorites, Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder, RootNavGraph.FAVORITES
    )

    data object Shopping: NavigationItem(
        R.string.shopping, Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, RootNavGraph.SHOPPING
    )

    data object Profile: NavigationItem(
        R.string.profile, Icons.Filled.AccountBox, Icons.Outlined.AccountBox, RootNavGraph.PROFILE
    )
}