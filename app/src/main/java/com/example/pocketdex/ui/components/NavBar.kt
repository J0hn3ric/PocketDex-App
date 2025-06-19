package com.example.pocketdex.ui.components

import androidx.annotation.StringRes
import androidx.collection.mutableIntSetOf
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pocketdex.R


/**
 * Enum class for the buttons in the Nav Bar (?, Home, Profile)
 */
enum class NavBarDestination(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector,
    val contentDescription: String
) {
    IDK(
        route = "",
        label = R.string.TODO,
        icon = Icons.Filled.Warning,
        contentDescription = "Button to be decided yet"),
    HOME(
        route = "home",
        label = R.string.home,
        icon = Icons.Filled.Home,
        contentDescription = "Home button for NavBar"),
    PROFILE(
        route = "profile",
        label = R.string.profile,
        icon = Icons.Filled.AccountCircle,
        contentDescription = "Profile button for NavBar")
}


/**
 * NavBar Composable
 */
@Composable
fun NavBar(
    navController: NavHostController,
    startDestination: NavBarDestination = NavBarDestination.HOME
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        NavBarDestination.entries.forEachIndexed { index, navBarDestination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    navController.navigate(route = navBarDestination.route)
                    selectedDestination = index
                },
                icon = {
                    Icon(
                        navBarDestination.icon,
                        navBarDestination.contentDescription
                    )
                },
                label = { Text(stringResource(navBarDestination.label)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    val navHostController = rememberNavController()

    MaterialTheme {
        NavBar(
            navController = navHostController
        )
    }
}