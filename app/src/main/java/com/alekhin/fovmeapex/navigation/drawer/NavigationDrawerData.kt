package com.alekhin.fovmeapex.navigation.drawer

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.FindReplace
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.graphics.vector.ImageVector
import com.alekhin.fovmeapex.R
import com.alekhin.fovmeapex.navigation.graph.Graph
import com.alekhin.fovmeapex.navigation.screen.Screen

data class NavigationDrawerData(val route: String, val imageVector: ImageVector, @StringRes val label: Int) {
    companion object {
        fun getNavigationDrawerDataList(): List<NavigationDrawerData> {
            return listOf(
                NavigationDrawerData(route = Screen.HOME, imageVector = Icons.Default.Home, label = R.string.home),
                NavigationDrawerData(route = Screen.CALCULATOR, imageVector = Icons.Default.Calculate, label = R.string.calculator),
                NavigationDrawerData(route = Screen.CONVERTER, imageVector = Icons.Default.FindReplace, label = R.string.sensitivity_converter),
                NavigationDrawerData(route = Graph.EDITOR, imageVector = Icons.Default.Construction, label = R.string.config_editor),
                NavigationDrawerData(route = "", imageVector = Icons.Default.Logout, label = R.string.sign_out),)
        }
    }
}