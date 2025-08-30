package com.example.pocketdex.ui.add_cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.compose.PocketDexTheme
import com.example.pocketdex.PocketDexScreen
import com.example.pocketdex.R
import com.example.pocketdex.ui.components.AddButton
import com.example.pocketdex.ui.components.ListOfCardsScreen
import com.example.pocketdex.ui.components.NavBar
import com.example.pocketdex.ui.components.NavBarDestination
import com.example.pocketdex.ui.components.TopAppBar
import com.example.pocketdex.ui.components.Type
import com.example.pocketdex.ui.mock_data.MockUserCards
import com.example.pocketdex.ui.mock_data.UserCardsDataSource
import com.example.pocketdex.ui.settings.SettingsScreen

enum class ButtonType {
    ADD,
    REMOVE
}

@Composable
fun AddCardScreen(
    modifier: Modifier,
    paddingValues: PaddingValues
) {
    val mockUserCards = UserCardsDataSource.mockUserCards

    ListOfCardsScreen(
        modifier = modifier
            .padding(paddingValues = paddingValues),
        typeOFScreen = Type.ADD_CARDS,
        cards = mockUserCards
    )
}





@Composable
fun AddCardComposable(
    modifier: Modifier = Modifier,
    card: MockUserCards
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
                painter = painterResource(card.imgRes),
                contentDescription = card.id,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(top = 4.dp)
                    .weight(17f)
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(4.dp))

            AddCardButtonRow(
                modifier = modifier.weight(3f)
            )

        }
    }
}



@Composable
fun AddCardButtonRow(
    modifier: Modifier = Modifier
) {
    val mockValue = remember { mutableStateOf("") } // change to viewmodel

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val buttonModifier = Modifier
            .fillMaxHeight()
            .weight(1f)

        if (mockValue.value == "") {
            DisabledButton(
                buttonType = ButtonType.REMOVE,
                modifier = buttonModifier
            )
        } else {
            EnabledButton(
                buttonType = ButtonType.REMOVE,
                modifier = buttonModifier,
                onClick = {
                    if (mockValue.value == "1") {
                        mockValue.value = ""
                    } else {
                        mockValue.value = (mockValue.value.toInt() - 1).toString()
                    }
                }
            )
        }

        BasicTextField(
            value = mockValue.value,
            onValueChange = { mockValue.value = it},
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.weight(2f)
        )

        if (mockValue.value == "999") {
            DisabledButton(
                buttonType = ButtonType.ADD,
                modifier = buttonModifier
            )
        } else {
            EnabledButton(
                buttonType = ButtonType.ADD,
                modifier = buttonModifier,
                onClick = {
                    if (mockValue.value == "") {
                        mockValue.value = "1"
                    } else {
                        mockValue.value = (mockValue.value.toInt() + 1).toString()
                    }
                }
            )
        }
    }
}

val removeButtonShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = 0.dp,
    bottomStart = 12.dp
)
val addButtonShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = 12.dp,
    bottomStart = 0.dp
)

@Composable
fun DisabledButton(
    modifier: Modifier = Modifier,
    buttonType: ButtonType
) {
    Box(
        modifier = modifier
            .background(
                color = Color.LightGray,
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = if (buttonType == ButtonType.ADD) {
                    addButtonShape
                } else {
                    removeButtonShape
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter =
                if (buttonType == ButtonType.ADD) {
                    painterResource(R.drawable.add_grey)
                } else {
                    painterResource(R.drawable.remove_grey)
                },
            contentDescription = "minus icon",
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun EnabledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonType: ButtonType
) {

    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = if (buttonType == ButtonType.ADD) {
                    addButtonShape
                } else {
                    removeButtonShape
                }
            )
            .clickable{
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = if (buttonType == ButtonType.ADD) {
                painterResource(R.drawable.add_black)
            } else {
                painterResource(R.drawable.remove_black)
            },
            contentDescription = "minus icon",
            modifier = Modifier.size(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddCardScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = PocketDexScreen.MANUAL_EDIT_COLLECTION

    PocketDexTheme {
        Scaffold(
            topBar = {
                if (currentScreen.hasTopBar) {
                    TopAppBar(
                        currentScreen = currentScreen,
                        canNavigateBack = currentScreen.navigableBack,
                        navigateUp = {},
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
            AddCardScreen(
                modifier = Modifier.fillMaxSize(),
                paddingValues = paddingValues
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardComposablePreview() {
    val mockUserCards = UserCardsDataSource.mockUserCards
    PocketDexTheme {
        AddCardComposable(
            card = mockUserCards.get(0)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddCardButtonRowPreview() {
    PocketDexTheme {
        AddCardButtonRow()
    }
}