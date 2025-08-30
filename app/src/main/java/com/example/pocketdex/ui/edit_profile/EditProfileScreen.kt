package com.example.pocketdex.ui.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.PocketDexTheme
import com.example.pocketdex.ui.PocketDexScreen
import com.example.pocketdex.ui.components.AddButton
import com.example.pocketdex.ui.components.NavBar
import com.example.pocketdex.ui.components.NavBarDestination
import com.example.pocketdex.ui.components.TopAppBar

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        /* TODO: design the settings screen */
        /*
        * Settings should allow for the user to edit
        * his profile, show faq, show news regarding ptcgp
        * */
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = PocketDexScreen.SETTINGS

    PocketDexTheme {
        Scaffold(
            topBar = {
                if (currentScreen.hasTopBar) {
                    TopAppBar(
                        currentScreen = currentScreen,
                        canNavigateBack = currentScreen.navigableBack,
                        navigateUp = {},
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
        ) { paddingValues ->
            SettingsScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
