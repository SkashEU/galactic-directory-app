package com.skash.galacticdirectory

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.skash.galacticdirectory.designsystem.component.BottomNavigationBar
import com.skash.galacticdirectory.designsystem.component.template.Screen
import com.skash.galacticdirectory.navigation.BottomNavItem
import com.skash.galacticdirectory.navigation.Screen
import com.skash.galacticdirectory.navigation.TopLevelScreen
import com.skash.galacticdirectory.navigation.topLevelGraph
import com.skash.galacticdirectory.resources.Res
import com.skash.galacticdirectory.resources.bottom_navigation_favorites
import com.skash.galacticdirectory.resources.bottom_navigation_home
import org.jetbrains.compose.resources.stringResource

private val APP_TABS = listOf(
    BottomNavItem(
        Res.string.bottom_navigation_home,
        TopLevelScreen.Home,
        Icons.Default.Home
    ),
    BottomNavItem(
        Res.string.bottom_navigation_favorites,
        TopLevelScreen.Favorites,
        Icons.Default.Favorite
    ),
)

@Composable
internal fun MainScreen(
    bottomNavController: NavHostController
) {

    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val selectedTab = remember(navBackStackEntry) {
        findSelectedTab(APP_TABS, navBackStackEntry?.destination)
    }

    Screen(
        bottomBar = {
            AppBottomBar(APP_TABS, selectedTab) {
                navigateTab(bottomNavController, it)
            }
        }
    ) {
        NavHost(
            navController = bottomNavController,
            startDestination = TopLevelScreen.Home,
        ) {
            topLevelGraph()
        }
    }
}

@Composable
private fun AppBottomBar(
    tabs: List<BottomNavItem>,
    selectedTab: BottomNavItem,
    onTabClicked: (BottomNavItem) -> Unit
) {
    BottomNavigationBar(
        items = tabs,
        selectedItem = selectedTab,
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = it.icon,
                contentDescription = stringResource(it.title)
            )
        },
        label = {
            Text(
                text = stringResource(it.title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        onItemSelected = onTabClicked
    )
}

private fun navigateTab(navController: NavHostController, tab: BottomNavItem) {
    navController.navigate(tab.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}

private fun findSelectedTab(
    tabs: List<BottomNavItem>,
    currentDestination: NavDestination?
): BottomNavItem = tabs.find { tab ->
    currentDestination?.hierarchy?.any { it.route == tab.route::class.qualifiedName } == true
} ?: tabs.first()