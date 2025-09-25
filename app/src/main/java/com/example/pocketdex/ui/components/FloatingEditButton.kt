package com.example.pocketdex.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.PocketDexTheme

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        AddMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        )
        SmallFloatingActionButton(
            onClick = { expanded = true },
            shape = CircleShape
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Composable
fun AddMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
    ) {
        DropdownMenuItem(
            text = { Text("Example1") },
            onClick = { onDismissRequest() }
        )
        DropdownMenuItem(
            text = { Text("Example2") },
            onClick = { onDismissRequest() }
        )
        DropdownMenuItem(
            text = { Text("Example3") },
            onClick = { onDismissRequest() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddMenuPreview() {
    var expanded by remember { mutableStateOf(true) }

    PocketDexTheme {
        AddMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddButtonPreview() {
    PocketDexTheme {
        AddButton()
    }
}