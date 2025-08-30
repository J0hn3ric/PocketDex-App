package com.example.pocketdex.ui

import android.annotation.SuppressLint
import android.media.Image
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.PocketDexTheme
import com.example.pocketdex.PocketDexScreen
import com.example.pocketdex.R
import com.example.pocketdex.ui.components.AddButton
import com.example.pocketdex.ui.components.ListOfCardsScreen
import com.example.pocketdex.ui.components.NavBar
import com.example.pocketdex.ui.components.TitleRow
import com.example.pocketdex.ui.components.TopAppBar
import com.example.pocketdex.ui.components.Type
import com.example.pocketdex.ui.mock_data.MockUserCards
import com.example.pocketdex.ui.mock_data.UserCardsDataSource

/**
 * Composable for the Home Screen:
 * Screen with all cards in the users' collection
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    val mockUserCards = UserCardsDataSource.mockUserCards

    ListOfCardsScreen(
        modifier = modifier
            .padding(paddingValues = paddingValues),
        typeOFScreen = Type.HOME,
        cards = mockUserCards
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = PocketDexScreen.HOME

    PocketDexTheme {
        Scaffold(
            topBar = { TopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = currentScreen.navigableBack,
                navigateUp = {},
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            ) },
            bottomBar = { NavBar(navController = navController) },
            floatingActionButton = {
                if (currentScreen == PocketDexScreen.HOME) {
                    AddButton()
                }
            }
        ) { paddingValues ->
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                paddingValues
            )
        }
    }
}

val mockUserCards = UserCardsDataSource.mockUserCards

/**
 * Composable for UserCard:
 * Shows the card image and quantity owned
 */
@Composable
fun UserCardComposable(
    userCard: MockUserCards,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.6f)
    ) {
        Column(
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(userCard.imgRes),
                contentDescription = userCard.id,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(top = 4.dp)
                    .weight(17f)
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "owned: ${userCard.quantity}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier
                    .weight(3f)
                    .fillMaxWidth()
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun UserCardCardPreview() {
    PocketDexTheme {
        UserCardComposable(
            userCard = mockUserCards.get(0)
        )
    }
}


