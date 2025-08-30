package com.example.pocketdex

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pocketdex.ui.add_cards.EditCollectionScreen
import com.example.pocketdex.ui.components.ActionButton
import com.example.pocketdex.ui.components.AddButton
import com.example.pocketdex.ui.components.NavBar
import com.example.pocketdex.ui.components.NavBarDestination
import com.example.pocketdex.ui.components.TopAppBar
import com.example.pocketdex.ui.home.HomeScreen
import com.example.pocketdex.ui.settings.SettingsScreen
import com.example.pocketdex.ui.start.StartScreen
import com.example.pocketdex.ui.user_profile.UserProfileScreen


enum class PocketDexScreen(
    @StringRes val screenTitle: Int = R.string.empty,
    val route: String,
    val navigableBack: Boolean,
    val hasTopBar: Boolean = true,
    val hasNavBar: Boolean = true
) {
    START(
        route = "start",
        navigableBack = false,
        hasTopBar = false,
        hasNavBar = false
    ),
    HOME(
        screenTitle = R.string.app_name,
        route = "home",
        navigableBack = false
    ),
    PROFILE(
        screenTitle = R.string.profile,
        route = "profile",
        navigableBack = true,
    ),
    SETTINGS(
        screenTitle = R.string.settings,
        route = "settings",
        navigableBack = true
    ),
    MANUAL_EDIT_COLLECTION(
        screenTitle = R.string.edit_collection,
        route = "edit_collection",
        navigableBack = true
    );

    companion object {
        fun fromRoute(route: String?): PocketDexScreen =
            entries.find { it.route == route } ?: HOME
    }
}

@Composable
fun PocketDexScreen.Action(navController: NavHostController) {
    when (this) {
        PocketDexScreen.HOME -> ActionButton(
            onClick = { /* TODO: mandare alla pagina di ricerca */ },
            imageVector = androidx.compose.material.icons.Icons.Default.Search,
            contentDescription = androidx.compose.ui.res.stringResource(R.string.search_button)
        )
        PocketDexScreen.PROFILE -> ActionButton(
            onClick = { navController.navigate(PocketDexScreen.SETTINGS.route) },
            imageVector = androidx.compose.material.icons.Icons.Default.Settings,
            contentDescription = androidx.compose.ui.res.stringResource(R.string.settings_button)
        )
        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketDexApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = PocketDexScreen.fromRoute(
        backStackEntry?.destination?.route
    )

    Scaffold(
        topBar = {
            if (currentScreen.hasTopBar) {
                TopAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    navController = navController,
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
                )
            }
        },
        bottomBar = {
            if (currentScreen.hasNavBar) {
                NavBar(
                    navController = navController,
                    startDestination = NavBarDestination.HOME
                )
            }
        },
        floatingActionButton = {
            if (currentScreen == PocketDexScreen.HOME) {
                AddButton()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PocketDexScreen.START.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = PocketDexScreen.START.route) {
                StartScreen(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }

            composable(route = PocketDexScreen.HOME.route) {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }

            composable(route = PocketDexScreen.PROFILE.route) {
                UserProfileScreen(
                     modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = PocketDexScreen.SETTINGS.route) {
                SettingsScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = PocketDexScreen.MANUAL_EDIT_COLLECTION.route) {
                EditCollectionScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}