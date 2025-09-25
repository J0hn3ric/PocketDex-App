package com.example.pocketdex.ui.profile

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.PocketDexTheme
import com.example.pocketdex.ui.PocketDexScreen
import com.example.pocketdex.R
import com.example.pocketdex.ui.AppViewModelProvider
import com.example.pocketdex.ui.components.ButtonRow
import com.example.pocketdex.ui.components.EditButton
import com.example.pocketdex.ui.components.NavBar
import com.example.pocketdex.ui.components.TopAppBar

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {

    val profileUiState by profileViewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
                .padding(top = 8.dp)
                .wrapContentHeight()
        ) {
            UserProfileInfo(
                usernameValue = profileUiState.username,
                friendIdValue = profileUiState.friendId,
                profileIcon = profileUiState.profileIcon
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ButtonRow(
            primaryButtonAction = { /* TODO: make the delete account dialog appear */ },
            secondaryButtonAction = { /* TODO: make logout request */ },
            primaryButtonText = stringResource(R.string.delete_account),
            secondaryButtonText = stringResource(R.string.logout),
            textStyle = MaterialTheme.typography.titleLarge,
            primaryButtonColors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.danger),
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            secondaryButtonColors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = colorResource(R.color.danger)
            ),
            secondaryButtonBorderStroke = BorderStroke(
                width = 3.dp,
                color = colorResource(R.color.danger)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
fun UserProfileInfo(
    modifier: Modifier = Modifier,
    usernameValue: String,
    friendIdValue: String,
    @DrawableRes profileIcon: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserProfileImage(profileIcon = profileIcon)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = usernameValue,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Black
        )

        FriendIdTextRow(
            modifier = Modifier
                .fillMaxWidth(),
            friendIdValue = friendIdValue
        )
    }
}

@Composable
fun UserProfileImage(
    modifier: Modifier = Modifier,
    @DrawableRes profileIcon: Int
) {
    Box(
        modifier = Modifier
            .size(120.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape)
                .border(
                    width = 3.dp,
                    color = Color.Black,
                    shape = CircleShape
                ),
            painter = painterResource(profileIcon), //
            contentDescription = "user icon",
        )
    }
}







@Composable
fun FriendIdTextRow(
    modifier: Modifier = Modifier,
    friendIdValue: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        FriendIdText(text = stringResource(R.string.friend_id))

        Spacer(modifier = Modifier.width(8.dp))

        FriendIdText(text = friendIdValue)

    }
}

@Composable
fun FriendIdText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        color = Color.Gray
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = PocketDexScreen.PROFILE

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
                    NavBar(navController = navController)
                }
            },
            floatingActionButton = {
                if (currentScreen == PocketDexScreen.HOME) {
                    EditButton(navController = navController)
                }
            }
        ) { paddingValues ->
            UserProfileScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}