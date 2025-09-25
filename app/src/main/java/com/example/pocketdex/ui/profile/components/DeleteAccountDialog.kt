package com.example.pocketdex.ui.user_profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.compose.PocketDexTheme
import com.example.pocketdex.R
import com.example.pocketdex.ui.components.ButtonRow

@Composable
fun DeleteAccountDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        DeleteAccountDialogContent(onDismissRequest = onDismissRequest)
    }
}

@Composable
fun DeleteAccountDialogContent(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.delete_account),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            HorizontalDivider(
                modifier = modifier
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 28.dp),
                thickness = 1.dp,
                color = Color.Black
            )

            DeleteAccountWarning()

            Spacer(Modifier.weight(1f))

            ButtonRow(
                primaryButtonAction = {
                    /* TODO: make request to delete
                        account then dismiss dialog */
                    onDismissRequest()
                },
                secondaryButtonAction = { onDismissRequest() },
                primaryButtonText = stringResource(R.string.delete_account),
                secondaryButtonText = stringResource(R.string.cancel),
                primaryButtonColors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.danger),
                    contentColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 4.dp)
            )
        }
    }
}


@Composable
fun DeleteAccountWarning(
    modifier: Modifier = Modifier
) {
    val warning = stringResource(R.string.delete_account_warning, "")
    val confirmationRequest = stringResource(R.string.delete_account_confirmation_request)

    Text(
        buildAnnotatedString {
            append(warning)
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(confirmationRequest)
            }
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.padding(horizontal = 28.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeleteAccountDialogPreview() {
    PocketDexTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DeleteAccountDialog {  }
        }
    }
}