package com.personal.features.adaptive_navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavigationScreen() {
    val screens = listOf(NavigationItem.Home, NavigationItem.Favorites, NavigationItem.Shopping, NavigationItem.Profile)
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            screens.fastForEach { item ->
                val isSelected = item.route == navBackStackEntry?.destination?.route
                item(
                    icon = {
                        AnimatedContent(
                            targetState = isSelected,
                            label = "Icon animation",
                            transitionSpec = { scaleIn(initialScale = .5f) + fadeIn() togetherWith scaleOut(targetScale = .5f) + fadeOut() }
                        ) { state ->
                            Icon(
                                imageVector = if (state) item.selectedIcon else item.unselectedIcon,
                                contentDescription = stringResource(id = item.label)
                            )
                        }
                    },
                    label = {
                        Text(text = stringResource(item.label))
                    },
                    selected = isSelected,
                    onClick = {
                        rootNavController.navigate(item.route) {
                            popUpTo(rootNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        NavHost(rootNavController, startDestination = RootNavGraph.HOME) {
            composable(RootNavGraph.HOME) {
                HomeNavHost()
            }
            composable(RootNavGraph.FAVORITES) {
                FavoritesNavHost()
            }
            composable(RootNavGraph.SHOPPING) {
                ShoppingNavHost()
            }
            composable(RootNavGraph.PROFILE) {
                ProfileNavHost()
            }
        }
    }
}

@Composable
fun HomeNavHost() {
    val homeNavController = rememberNavController()
    NavHost(navController = homeNavController, startDestination = "home1") {
        for(i in 1..10) {
            composable("home$i") {
                GenericScreen(
                    text = "Home $i",
                    onNextClick = {
                        if(i < 10) {
                            homeNavController.navigate("home${i + 1}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun FavoritesNavHost() {
    val chatNavController = rememberNavController()
    NavHost(chatNavController, startDestination = "favorites1") {
        for(i in 1..10) {
            composable("favorites$i") {
                GenericScreen(
                    text = "Favorites $i",
                    onNextClick = {
                        if(i < 10) {
                            chatNavController.navigate("favorites${i + 1}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ShoppingNavHost() {
    val settingsNavController = rememberNavController()
    NavHost(settingsNavController, startDestination = "shopping1") {
        for(i in 1..10) {
            composable("shopping$i") {
                GenericScreen(
                    text = "Shopping $i",
                    onNextClick = {
                        if(i < 10) {
                            settingsNavController.navigate("shopping${i + 1}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileNavHost() {
    val settingsNavController = rememberNavController()
    NavHost(settingsNavController, startDestination = "profile1") {
        for(i in 1..10) {
            composable("profile$i") {
                GenericScreen(
                    text = "Profile $i",
                    onNextClick = {
                        if(i < 10) {
                            settingsNavController.navigate("profile${i + 1}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GenericScreen(
    text: String,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(text = text)
        Button(onClick = onNextClick) {
            Text("Next")
        }
    }
}

object RootNavGraph {
    const val HOME = "home_graph"
    const val FAVORITES = "favorites_graph"
    const val SHOPPING = "shopping_graph"
    const val PROFILE = "profile_graph"
}