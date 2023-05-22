package com.alekhin.fovmeapex.editor.room.file

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alekhin.fovmeapex.R
import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import com.alekhin.fovmeapex.navigation.screen.Screen

// region SCREEN

@Composable
fun ConfigEditorScreen(navController: NavHostController, configFileViewModel: ConfigFileViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            ConfigEditorTopBar {
                // TODO: ADD SORT
            }
        },
        floatingActionButton = {
            ConfigEditorFloatingActionButton {
                navController.navigate(Screen.EDIT_FILE)
            }
        }) {
        ConfigEditorContent(
            contentPadding = it,
            navController = navController,
            configFileViewModel = configFileViewModel
        )
    }
}

// endregion

// region COMPONENTS

// region TOP BAR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfigEditorTopBar(onClick: () -> Unit) {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.config_files),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            FilledIconButton(
                onClick = onClick,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Sort, contentDescription = null)
            }
        }
    })
}

// endregion

// region CONTENT

@Composable
private fun ConfigEditorContent(contentPadding: PaddingValues, configFileViewModel: ConfigFileViewModel, navController: NavHostController) {
    LazyColumn(contentPadding = contentPadding) {
        items(items = configFileViewModel.state.value.configFiles) { configFile ->
            ConfigEditorItem(
                configFile = configFile,
                onDeleteClick = {
                    configFileViewModel.onEvent(
                        ConfigFileEvent.DeleteConfigFile(configFile)
                    )
                }) {
                navController.navigate(Screen.EDIT_FILE + "?configFileId=${configFile.id}")
            }
        }
    }
}

@Composable
private fun ConfigEditorItem(configFile: ConfigFile, onDeleteClick: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = configFile.name,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = configFile.content,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 10
        )
        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

// endregion

// region FLOATING ACTION BUTTON

@Composable
private fun ConfigEditorFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

// endregion

// endregion