package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.rickandmorty.ui.screens.commonUtils.GetPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterData(
    genderVal: String,
    statusVal: String,
    applyFilter: () -> Unit,
    changeGender: (String) -> Unit,
    changeStatus: (String) -> Unit,
    modifier: Modifier = Modifier,
    close: ModalBottomSheetState,

) {
    var genderState by remember {
        mutableStateOf("null")
    }
    var aliveState by remember {
        mutableStateOf("null")
    }

    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        val statusList = listOf<String>("All", "Alive", "dead", "unknown")
        val genderList = listOf<String>("All", "Male", "Female", "Genderless", "unknown")
        Column(
            modifier = Modifier
                .padding(top = 14.dp)
                .fillMaxWidth()
        ) {
            genderState = DropdownDemo(
                options = genderList,
                tag = "Gender",
                selectedValue = genderVal,
                setup = changeGender
            )
            Spacer(modifier = Modifier.height(12.dp))
            aliveState = DropdownDemo(
                options = statusList,
                tag = "Status",
                selectedValue = statusVal,
                setup = changeStatus
            )
        }

        Row(
            modifier = Modifier.padding(all = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "applyFilter" },
                onClick = {
                    applyFilter()
                    scope.launch { close.hide() }
                }
            ) {
                Text("Click to Apply")
            }
        }
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
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()

        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        setup(selectionOption)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        text = selectionOption,
                        modifier = Modifier
                            .padding(14.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
    return selectedOptionText
}