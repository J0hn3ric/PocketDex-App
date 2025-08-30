package com.example.pocketdex.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.compose.PocketDexTheme
import com.example.pocketdex.PocketDexScreen
import com.example.pocketdex.R
import com.example.pocketdex.ui.components.AddButton
import com.example.pocketdex.ui.components.NavBar
import com.example.pocketdex.ui.components.NavBarDestination
import com.example.pocketdex.ui.components.TopAppBar

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    Column(
        modifier = modifier
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    start = 80.dp,
                    end = 80.dp
                )
                .wrapContentHeight()
        ) {
            UserProfileInfo()
        }

        Spacer(modifier = Modifier.weight(1f))

        UserProfileButtonRow(modifier = Modifier.padding(bottom = 8.dp))
    }
}

@Composable
fun UserProfileInfo(
    modifier: Modifier = Modifier
) {
    val usernameValue = remember { mutableStateOf("Username") } // add a viewmodel
    val friendIdValue = remember { mutableStateOf("Friend ID") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserProfileImage()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = usernameValue.value,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )

        FriendIdTextRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            friendIdValue = friendIdValue
        )
    }
}

@Composable
fun UserProfileImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(120.dp)
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape)
                .border(
                    width = 3.dp,
                    color = Color.Black,
                    shape = CircleShape
                ),
            painter = painterResource(R.drawable.baseline_person_24), //
            contentDescription = "user icon",
        )

        EditUserProfileImageButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 5.dp,
                    end = 5.dp
                ),
            onClick = { /* TODO */ }
        )
    }
}

@Composable
fun UserProfileButtonRow(
    modifier: Modifier = Modifier
) {
    val primaryButtonsColor = Color(
        red = 170,
        green = 0,
        blue = 0
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /* TODO */ },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryButtonsColor,
                contentColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            UserProfileButtonContent(text = stringResource(R.string.delete_account))
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { /* TODO */ },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = primaryButtonsColor
            ),
            border = BorderStroke(
                width = 3.dp,
                color = primaryButtonsColor
            )
        ) {
            UserProfileButtonContent(text = stringResource(R.string.logout))
        }
    }
}







@Composable
fun FriendIdTextRow(
    modifier: Modifier = Modifier,
    friendIdValue: MutableState<String>
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        FriendIdText(text = stringResource(R.string.friend_id))

        Spacer(modifier = Modifier.width(4.dp))

        FriendIdText(text = friendIdValue.value)

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

@Composable
fun EditUserProfileImageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .clip(shape = CircleShape)
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = CircleShape
            )
            .background(color = Color.LightGray)
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            painter = painterResource(R.drawable.edit_icon),
            contentDescription = "edit profile image"
        )
    }
}

@Composable
fun UserProfileButtonContent(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = PocketDexScreen.PROFILE

    PocketDexTheme {
        Scaffold(
            topBar = { TopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = currentScreen.navigableBack,
                navigateUp = {},
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            ) },
            bottomBar = {
                NavBar(
                    navController = navController,
                    startDestination = NavBarDestination.PROFILE
                )
            },
            floatingActionButton = {
                if (currentScreen == PocketDexScreen.HOME) {
                    AddButton()
                }
            }
        ) { paddingValues ->
            UserProfileScreen(
                modifier = Modifier.fillMaxSize(),
                paddingValues = paddingValues
            )
        }
    }
}