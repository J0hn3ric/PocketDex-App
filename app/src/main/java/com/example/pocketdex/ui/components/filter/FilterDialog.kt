package com.example.pocketdex.ui.components

import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.compose.PocketDexTheme
import com.example.pocketdex.R

enum class FilterChosen {
    BY_EXPANSION,
    BY_PACK
}

@Composable
fun FilterDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        FilterDialogContent(onDismissRequest = onDismissRequest)
    }
}

@Composable
fun FilterDialogContent(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val mockName = remember { mutableStateOf("") } // change to viewmodel

    Card(
        modifier =  modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.filter),
                style = MaterialTheme.typography.titleLarge
            )

            HorizontalDivider(
                modifier = modifier
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 24.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            SearchBar(
                value = mockName.value,
                onValueChange = { mockName.value = it}
            )

            RarityRow(
                modifier = modifier
                    .padding(vertical = 16.dp)
            )

            FilterBy(
                modifier = modifier.weight(1f)
            )

            ButtonRow(
                primaryButtonAction = {
                    /* TODO: filter based on this then dismiss dialog */
                    onDismissRequest()
                },
                secondaryButtonAction = { onDismissRequest() },
                primaryButtonText = stringResource(R.string.apply),
                secondaryButtonText = stringResource(R.string.cancel),
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 4.dp)
            )
        }
    }
}



/*
* Composable for the SearchBar inside the
* filter window
*/
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .padding(horizontal = 20.dp)
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(40.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_by_name),
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 16.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}




/*
* Composable for the Rarity filter selection menu
*/
@Composable
fun RarityRow(
    modifier: Modifier = Modifier
) {
    val rarity = R.array.packs_array

    val expanded = remember { mutableStateOf(false) }

    val text = remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.rarity),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.width(16.dp))

        FilterDropDownButton(
            dropdownRes = rarity,
            expanded = expanded,
            offset = DpOffset(x = 0.dp, y = (-8).dp),
            text = text,
            button = {RarityButton(
                onClick = { expanded.value = true },
                text = text
            )},
            modifier = Modifier
                .height(120.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .width(128.dp),
        )
    }
}

@Composable
fun RarityButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: MutableState<String>,
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(width = 1.dp, color = Color.Black),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = text.value,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .width(120.dp)
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = ""
            )
        }
    }
}




/*
* Composable for filtering by expansion
* and by pack
*/
@Composable
fun FilterBy(
    modifier: Modifier = Modifier
) {
    val filterChosen = remember { mutableStateOf(FilterChosen.BY_EXPANSION) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.filter_by),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 8.dp)
        )

        ChoicePillButton(filterChosen = filterChosen)

        FilterByExpansionOrByPack(
            filterChosen = filterChosen,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ChoicePillButton(
    modifier: Modifier = Modifier,
    filterChosen: MutableState<FilterChosen>
) {
    Row(
        modifier = modifier
    ) {
        ChoiceButton(
            filterChosen = filterChosen,
            text = R.string.by_expansion,
            targetFilter = FilterChosen.BY_EXPANSION
        )
        ChoiceButton(
            filterChosen = filterChosen,
            text = R.string.by_pack,
            targetFilter = FilterChosen.BY_PACK
        )
    }
}

@Composable
fun ChoiceButton(
    modifier: Modifier = Modifier,
    filterChosen: MutableState<FilterChosen>,
    @StringRes text: Int,
    targetFilter: FilterChosen
) {
    Button(
        onClick = {
            // make it so that when state changes, the text inside the button changes
            filterChosen.value = targetFilter
        },
        shape = if (targetFilter == FilterChosen.BY_EXPANSION) {
            RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            )
        } else {
            RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp
            )
        },
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 0.dp,
            end = 8.dp,
            bottom = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (filterChosen.value == targetFilter) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            },
            contentColor = if (filterChosen.value == targetFilter) {
                Color.White
            } else {
                MaterialTheme.colorScheme.primary
            },
        ),
        border = if (filterChosen.value == targetFilter) {
            null
        } else {
            BorderStroke(width = 1.dp, color = Color.Black)
        },
        modifier = Modifier
            .height(28.dp)
    ) {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleSmall,
            color = if (filterChosen.value == targetFilter) {
                Color.White
            } else {
                Color.Black
            },
            modifier = Modifier.padding(vertical = 0.dp)
        )
    }
}


@Composable
fun FilterByExpansionOrByPack(
    modifier: Modifier = Modifier,
    filterChosen: MutableState<FilterChosen>
) {
    val expansions = R.array.expansions_array

    val packs = R.array.packs_array

    val expanded = remember { mutableStateOf(false) } // change this with viewmodel

    val text = remember { mutableStateOf("") }

    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        FilterDropDownButton(
            button = {FilterByExpansionOrByPackButton(
                expanded = expanded,
                text = text
            )},
            dropdownRes = if (filterChosen.value == FilterChosen.BY_EXPANSION) {
                expansions
            } else {
                packs
            },
            expanded = expanded,
            offset = DpOffset(x = 0.dp, y = (-8).dp),
            modifier = Modifier
                .height(120.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .width(200.dp),
            text = text
        )
    }
}


@Composable
fun FilterByExpansionOrByPackButton(
    expanded: MutableState<Boolean>,
    text: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = { expanded.value = true },
        border = BorderStroke(width = 1.dp, color = Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.value,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = ""
            )
        }
    }
}


/*
* Reusable Composable for dropdown menu
* and button, used for filtering by pack
* or expansion and for filtering by rarity
*/
@Composable
fun FilterDropDownButton(
    modifier: Modifier = Modifier,
    @ArrayRes dropdownRes: Int,
    expanded: MutableState<Boolean>,
    offset: DpOffset,
    text: MutableState<String>,
    button: @Composable () -> Unit
) {
    val context = LocalContext.current
    val dropdownItems: Array<String> = context.resources.getStringArray(dropdownRes)


    Box {
        button()

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                text.value = ""
                expanded.value = false
            },
            offset = offset,
            modifier = modifier
        ) {
            dropdownItems.forEach { title ->
                DropdownMenuItem(
                    text = {
                        Text(text = title)
                    },
                    onClick = {
                        expanded.value = false
                        text.value = title
                    },
                    modifier = Modifier
                        .height(32.dp)
                        .background(
                            color = if (text.value == title) {
                                Color.LightGray
                            } else {
                                MaterialTheme.colorScheme.background
                            }
                        )
                )
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilterDialogPreview() {
    PocketDexTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FilterDialog { /* TODO */ }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RarityRowPreview() {
    PocketDexTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            RarityRow()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FilterByPreview() {
    PocketDexTheme {
        FilterBy()
    }
}