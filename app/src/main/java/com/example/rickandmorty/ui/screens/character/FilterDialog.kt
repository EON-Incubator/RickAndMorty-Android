package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.screens.commonUtils.GetPadding
import kotlinx.coroutines.launch

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
        mutableStateOf("")
    }
    var aliveState by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        val statusList = listOf<String>(stringResource(R.string.all), stringResource(R.string.alive), stringResource(id = R.string.dead), stringResource(id = R.string.unknown))
        val genderList = listOf<String>(stringResource(id = R.string.all), stringResource(id = R.string.male), stringResource(id = R.string.female), stringResource(id = R.string.genderless), stringResource(id = R.string.unknown))
        Column(
            modifier = Modifier
                .padding(top = GetPadding().xxMediumPadding)
                .fillMaxWidth()
        ) {
            genderState = DropdownDemo(
                options = genderList,
                tag = stringResource(R.string.gender),
                selectedValue = genderVal,
                setup = changeGender
            )
            Spacer(modifier = Modifier.height(GetPadding().xMediumPadding))
            aliveState = DropdownDemo(
                options = statusList,
                tag = stringResource(R.string.status),
                selectedValue = statusVal,
                setup = changeStatus
            )
        }

        Row(
            modifier = Modifier.padding(all = GetPadding().smallPadding),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = R.string.apply_filter.toString() }
                    .testTag(stringResource(id = R.string.apply_filter)),
                onClick = {
                    applyFilter()
                    scope.launch { close.hide() }
                }
            ) {
                Text(stringResource(R.string.click_to_apply))
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
                .padding(GetPadding().xxxMediumPadding)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .padding(GetPadding().xxxMediumPadding)
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
                            .padding(GetPadding().xxxMediumPadding)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
    return selectedOptionText
}