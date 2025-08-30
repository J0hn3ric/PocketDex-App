package com.example.pocketdex.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pocketdex.R

@Composable
fun SearchActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_button)
        )
    }
}