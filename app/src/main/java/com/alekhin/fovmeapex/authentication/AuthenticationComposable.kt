package com.alekhin.fovmeapex.authentication

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

// region CARD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthCard(@StringRes id: Int, input: MutableState<String>, leadingIcon: @Composable () -> Unit, visualTransformation: VisualTransformation, keyboardType: KeyboardType) {
    TextField(
        value = input.value,
        onValueChange = { input.value = it },
        modifier = Modifier.padding(vertical = 8.dp),
        label = { Text(text = stringResource(id = id)) },
        leadingIcon = leadingIcon,
        isError = false,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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