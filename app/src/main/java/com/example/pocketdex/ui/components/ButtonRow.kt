package com.example.pocketdex.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.PocketDexTheme
import com.example.pocketdex.R

/*
* Composable for the Confirm and
* Cancel button row
*/
@Composable
fun ButtonRow(
    modifier: Modifier = Modifier,
    primaryButtonAction: () -> Unit,
    secondaryButtonAction: () -> Unit,
    primaryButtonText: String,
    secondaryButtonText: String,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    primaryButtonColors: ButtonColors = ButtonDefaults.buttonColors(),
    secondaryButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = Color.Gray
    ),
    secondaryButtonBorderStroke: BorderStroke? = BorderStroke(
        width = 3.dp,
        color = Color.Gray
    )
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Button(
            onClick = { primaryButtonAction() },
            colors = primaryButtonColors
        ) {
            Text(
                text = primaryButtonText,
                style = textStyle
            )
        }

        Spacer(
            modifier = Modifier
                .width(8.dp)
        )

        Button(
            onClick = { secondaryButtonAction() },
            colors = secondaryButtonColors,
            border = secondaryButtonBorderStroke
        ) {
            Text(
                text = secondaryButtonText,
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogButtonRowPreview() {
    PocketDexTheme {
        ButtonRow(
            primaryButtonAction = { /* Confirm action */ },
            secondaryButtonAction = { /* Dismiss action */ },
            primaryButtonText = stringResource(R.string.apply),
            secondaryButtonText = stringResource(R.string.cancel)
        )
    }
}