package com.alekhin.fovmeapex.navigation.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.navigation.graph.Graph

// region DRAWER

@Composable
fun NavigationDrawer(mainNavController: NavHostController, navController: NavHostController, authViewModel: AuthViewModel, content: @Composable () -> Unit) {
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            DrawerContent(
                mainNavController = mainNavController,
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }, content = content)
}

// endregion

// region CONTENT

@Composable
private fun DrawerContent(mainNavController: NavHostController, navController: NavHostController, authViewModel: AuthViewModel) {
    val navigationDrawerDataList = NavigationDrawerData.getNavigationDrawerDataList()
    val currentNavigationDrawerData = remember { mutableStateOf(value = 0) }

    navigationDrawerDataList.forEachIndexed { index, navigationDrawerData ->
        NavigationDrawerItem(label = { Text(text = stringResource(id = navigationDrawerData.label)) },
            selected = currentNavigationDrawerData.value == index,
            onClick = {
                if (index != navigationDrawerDataList.size - 1) {
                    mainNavController.navigate(route = navigationDrawerData.route)
                    currentNavigationDrawerData.value = index
                } else {
                    authViewModel.logout()
                    navController.navigate(route = Graph.ROOT) {
                        popUpTo(route = Graph.ROOT) {
                            inclusive = true
                        }
                    }
                }

            },
            modifier = Modifier.padding(horizontal = 12.dp),
            icon = {
                Icon(
                    imageVector = navigationDrawerData.imageVector,
                    contentDescription = null
                )
            })
    }
}

// endregion