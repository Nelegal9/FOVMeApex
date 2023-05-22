package com.alekhin.fovmeapex.computer

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alekhin.fovmeapex.ui.theme.Typography

// region EXPANDABLE LIST

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableTypeList(text: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.animateContentSize(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing))) {
        Row {
            Text(text = text, modifier = Modifier.align(Alignment.CenterVertically), fontSize = Typography.titleMedium.fontSize)
            IconButton(onClick = { expanded = !expanded }) {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        }

        if (expanded) content()
    }
}

// endregion

// region DROPDOWN MENU

// region INPUT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified T : Enum<T>> InputTypeDropdownMenuCard(@StringRes stringId: Int, @ArrayRes arrayId: Int, inputType: MutableState<T>) {
    var expanded by remember { mutableStateOf(false) }
    val inputTypeOptions = stringArrayResource(id = arrayId)
    var value by remember { mutableStateOf(inputTypeOptions[0]) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
        TextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier.menuAnchor().padding(vertical = 8.dp),
            readOnly = true,
            label = { Text(text = stringResource(id = stringId)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            inputTypeOptions.forEachIndexed { index, it ->
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    value = it
                    inputType.value = enumValues<T>()[index]
                    expanded = false
                })
            }
        }
    }
}

// endregion

// region RESULT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultTypeDropdownMenuCard(@StringRes stringId: Int, @ArrayRes arrayId: Int, resultIndex: MutableState<Int>) {
    var expanded by remember { mutableStateOf(false) }
    val inputTypeOptions = stringArrayResource(id = arrayId)
    var value by remember { mutableStateOf(inputTypeOptions[0]) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
        TextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier.menuAnchor().padding(vertical = 8.dp),
            readOnly = true,
            label = { Text(text = stringResource(id = stringId)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            inputTypeOptions.forEachIndexed { index, it ->
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    value = it
                    resultIndex.value = index
                    expanded = false
                })
            }
        }
    }
}

// endregion

// endregion

// region CARD

// region INPUT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCard(@StringRes id: Int, input: MutableState<Double>) {
    TextField(
        value = input.value.toString(),
        onValueChange = { try { input.value = it.toDouble() } catch (numberFormatException: NumberFormatException) {  } },
        modifier = Modifier.padding(vertical = 8.dp),
        label = { Text(text = stringResource(id = id)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        )
    )
}

// endregion

// region RESULT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultCard(@StringRes id: Int, result: MutableState<DoubleArray>, index: Int) {
    TextField(
        value = result.value[index].toString(),
        onValueChange = { result.value[index] = it.toDouble() },
        modifier = Modifier.padding(vertical = 8.dp),
        label = { Text(text = stringResource(id = id)) },
        readOnly = true,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        )
    )
}

// endregion

// endregion