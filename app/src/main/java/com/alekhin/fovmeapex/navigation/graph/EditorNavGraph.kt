package com.alekhin.fovmeapex.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.alekhin.fovmeapex.editor.room.file.ConfigEditorScreen
import com.alekhin.fovmeapex.editor.room.edit.ConfigFileEditorScreen
import com.alekhin.fovmeapex.navigation.screen.Screen

fun NavGraphBuilder.editorNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.CONFIG_EDITOR, route = Graph.EDITOR) {
        composable(route = Screen.CONFIG_EDITOR) {
            ConfigEditorScreen(navController = navController)
        }

        composable(route = Screen.EDIT_FILE + "?configFileId={configFileId}", arguments = listOf(
            navArgument(name = "configFileId") {
                type = NavType.IntType
                defaultValue = -1
            })) {
            ConfigFileEditorScreen(navController = navController)
        }
    }
}