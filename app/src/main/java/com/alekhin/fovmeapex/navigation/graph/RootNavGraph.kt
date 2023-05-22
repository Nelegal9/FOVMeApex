package com.alekhin.fovmeapex.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alekhin.fovmeapex.core.MainScreen
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.navigation.screen.Screen
import com.alekhin.fovmeapex.onboarding.OnBoardingScreen

@Composable
fun RootNavGraph(navController: NavHostController, startDestination: String, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = startDestination, route = Graph.ROOT) {
        composable(route = Screen.ON_BOARDING) {
            OnBoardingScreen(navController = navController)
        }

        authNavGraph(navController = navController, authViewModel = authViewModel)
        composable(route = Graph.MAIN) {
            MainScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}