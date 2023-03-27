package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogBox(selectGender: (code: String, id: String) -> Unit) {
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
                Text(text = "Filter for Characters")
            },
            text = {
                val statusList = listOf<String>("All", "Alive", "dead", "unknown")
                val genderList = listOf<String>("All", "Male", "Female", "Genderless", "unknown")
                Column() {
                    genderState = DropdownDemo(options = statusList, tag = "Status")
                    Spacer(modifier = Modifier.height(12.dp))
                    aliveState = DropdownDemo(options = genderList, tag = "Gender")
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            showDialog.value = false;
                            selectGender(genderState, aliveState)
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
fun DropdownDemo(options: List<String>, tag: String)//, selectGender:(code:String)->Unit) {
        : String {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    // selectGender(selectedOptionText)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("${tag}") },
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
//@Preview
//@Composable
//fun showit() {
//    Column() {
//
//
//        buttonGroup(buttonsInfo = listOf("men", "people"))
//        buttonGroup(buttonsInfo = listOf("Male", "Female", "Genderless", "unknown"))
//    }
//}
