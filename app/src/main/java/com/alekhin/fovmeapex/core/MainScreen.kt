package com.alekhin.fovmeapex.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.navigation.drawer.NavigationDrawer
import com.alekhin.fovmeapex.navigation.graph.MainNavGraph

// region SCREEN

@Composable
fun MainScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val mainNavController = rememberNavController()

    NavigationDrawer(
        mainNavController = mainNavController,
        navController = navController,
        authViewModel = authViewModel
    ) {
        MainNavGraph(navController = mainNavController, authViewModel = authViewModel)
    }
}

// endregion