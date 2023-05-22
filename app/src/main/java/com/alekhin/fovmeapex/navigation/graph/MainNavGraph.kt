package com.alekhin.fovmeapex.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.computer.calculator.CalculatorScreen
import com.alekhin.fovmeapex.computer.converter.ConverterScreen
import com.alekhin.fovmeapex.core.HomeScreen
import com.alekhin.fovmeapex.navigation.screen.Screen

@Composable
fun MainNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = Screen.HOME, route = Graph.MAIN) {
        composable(route = Screen.HOME) {
            HomeScreen(authViewModel = authViewModel)
        }

        composable(route = Screen.CALCULATOR) {
            CalculatorScreen()
        }

        composable(route = Screen.CONVERTER) {
            ConverterScreen()
        }

        editorNavGraph(navController = navController)
    }
}