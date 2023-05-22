package com.alekhin.fovmeapex.editor.room.edit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alekhin.fovmeapex.editor.room.UIEvent
import kotlinx.coroutines.flow.collectLatest

// region SCREEN

@Composable
fun ConfigFileEditorScreen(navController: NavController, addEditConfigFileViewModel: AddEditConfigFileViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        addEditConfigFileViewModel.eventFLow.collectLatest { event ->
            when (event) {
                UIEvent.SaveConfigFile -> navController.navigateUp()
                is UIEvent.ShowSnackbar -> TODO()
            }
        }
    }

    Scaffold(modifier = Modifier.padding(all = 16.dp), floatingActionButton = {
        ConfigFileEditorFloatingActionButton {
            addEditConfigFileViewModel.onEvent(
                AddEditConfigFileEvent.SaveConfigFile
            )
        }
    }) {
        ConfigFileEditorContent(
            paddingValues = it,
            addEditConfigFileViewModel = addEditConfigFileViewModel
        )
    }
}

// endregion

// region CONTENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfigFileEditorContent(paddingValues: PaddingValues, addEditConfigFileViewModel: AddEditConfigFileViewModel) {
    Card(modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues)) {
        TextField(
            value = addEditConfigFileViewModel.configFileName.value.text,
            onValueChange = {
                addEditConfigFileViewModel.onEvent(
                    AddEditConfigFileEvent.EnteredName(
                        it
                    )
                )
            },
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                .onFocusChanged {
                    addEditConfigFileViewModel.onEvent(AddEditConfigFileEvent.ChangeNameFocus(it))
                },
            placeholder = { Text(text = addEditConfigFileViewModel.configFileName.value.hint) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = addEditConfigFileViewModel.configFileContent.value.text,
            onValueChange = {
                addEditConfigFileViewModel.onEvent(
                    AddEditConfigFileEvent.EnteredContent(
                        it
                    )
                )
            },
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                .onFocusChanged {
                    addEditConfigFileViewModel.onEvent(AddEditConfigFileEvent.ChangeContentFocus(it))
                },
            placeholder = { Text(text = addEditConfigFileViewModel.configFileContent.value.hint) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )
    }
}

// endregion

// region FLOATING ACTION BUTTON

@Composable
private fun ConfigFileEditorFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null)
    }
}

// endregion