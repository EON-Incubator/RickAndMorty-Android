package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

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
                Text(text = stringResource(R.string.filter_for_characters), Modifier.padding(bottom = 12.dp))
            },
            text = {
                val statusList = listOf<String>(
                    stringResource(R.string.all),
                    stringResource(R.string.alive),
                    stringResource(
                        R.string.dead
                    ),
                    stringResource(R.string.unknown)
                )
                val genderList = listOf<String>(
                    stringResource(R.string.all),
                    stringResource(R.string.male),
                    stringResource(
                        R.string.female
                    ),
                    stringResource(R.string.genderless),
                    stringResource(R.string.unknown)
                )
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    genderState = DropdownDemo(
                        options = genderList,
                        tag = stringResource(R.string.gender),
                        selectedValue = genderVal,
                        setup = changeGender

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    aliveState = DropdownDemo(
                        options = statusList,
                        tag = stringResource(R.string.status),
                        selectedValue = statusVal,
                        setup = changeStatus
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth().semantics { contentDescription = "applyFilter" },
                        onClick = {
                            selectGender()
                            showDialog.value = false
                        }
                    ) {
                        Text(stringResource(R.string.click_to_apply))
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