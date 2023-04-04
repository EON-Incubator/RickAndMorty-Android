package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.screens.commonUtils.GetPadding

@Composable
fun DialogBox(
    genderVal: String,
    statusVal: String,
    selectGender: () -> Unit,
    changeGender: (String) -> Unit,
    changeStatus: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var genderState by remember {
        mutableStateOf("null")
    }
    var aliveState by remember {
        mutableStateOf("null")
    }
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = "Filter for Characters", Modifier.padding(bottom = GetPadding().xMediumPadding))
            },
            text = {
                val statusList = listOf<String>("All", "Alive", "dead", "unknown")
                val genderList = listOf<String>("All", "Male", "Female", "Genderless", "unknown")
                Column(modifier = Modifier.padding(top = GetPadding().xxMediumPadding)) {
                    genderState = DropdownDemo(
                        options = genderList,
                        tag = "Gender",
                        selectedValue = genderVal,
                        setup = changeGender

                    )
                    Spacer(modifier = Modifier.height(GetPadding().xMediumPadding))
                    aliveState = DropdownDemo(
                        options = statusList,
                        tag = "Status",
                        selectedValue = statusVal,
                        setup = changeStatus
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = GetPadding().smallPadding),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics { contentDescription = "applyFilter" },
                        onClick = {
                            selectGender()
                            showDialog.value = false
                        }
                    ) {
                        Text("Click to Apply")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo(
    options: List<String>,
    tag: String,
    selectedValue: String,
    setup: (String) -> Unit,
): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text("$tag") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded

                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        setup(selectionOption)
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
    return selectedOptionText
}