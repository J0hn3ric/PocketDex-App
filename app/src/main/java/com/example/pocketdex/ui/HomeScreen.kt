package com.example.pocketdex.ui

import android.media.Image
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocketdex.R
import com.example.pocketdex.ui.mock_data.MockUserCards
import com.example.pocketdex.ui.mock_data.UserCardsDataSource

/**
 * Composable for the Home Screen:
 * Screen with all cards in the users' collection
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val mockUserCards = UserCardsDataSource.mockUserCards
    Column(
        modifier = modifier
            .padding(4.dp)
    ) {
        TitleRow()
        Spacer(Modifier.height(4.dp))
        CollectionComposable(mockUserCards)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        )
    }
}

/**
 * Composable for the title row (the row with the title and filter button)
 */
@Composable
fun TitleRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineMedium
        )

        IconButton(
            onClick = { /* TODO */ }
        ) {
            Icon(
                painter = painterResource(R.drawable.drawable_filter_icon),
                contentDescription = stringResource(R.string.filter_button)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleRowPreview() {
    MaterialTheme {
        TitleRow()
    }
}

val mockUserCards = UserCardsDataSource.mockUserCards

/**
 * Composable for collection showing a scrollable
 * grid of cards owned by the user and quantity
 */
@Composable
fun CollectionComposable(
    cards: List<MockUserCards>,
    modifier: Modifier = Modifier
) {
    CollectionGrid(cards, modifier)
}

/**
 * Composable for the grid for the collection:
 */
@Composable
fun CollectionGrid(
    cards: List<MockUserCards>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        items(cards) { userCard ->
            UserCardCard(userCard)
        }
    }
}

/**
 * Composable for UserCard:
 * Shows the card image and quantity owned
 */
@Composable
fun UserCardCard(
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

            Spacer(Modifier.height(4.dp))

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
fun CollectionPreview() {
    MaterialTheme {
        CollectionComposable(
            cards = mockUserCards,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .navigationBarsPadding()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardCardPreview() {
    MaterialTheme {
        UserCardCard(
            userCard = mockUserCards.get(0)
        )
    }
}
