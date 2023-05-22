package com.alekhin.fovmeapex.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.authentication.login.LoginScreen
import com.alekhin.fovmeapex.authentication.registration.RegistrationScreen
import com.alekhin.fovmeapex.authentication.registration.TermsAndConditionsScreen
import com.alekhin.fovmeapex.navigation.screen.Screen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    navigation(startDestination = Screen.LOGIN, route = Graph.AUTHENTICATION) {
        composable(route = Screen.LOGIN) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }

        composable(route = Screen.FORGOT_PASSWORD) {
            //ForgotPasswordScreen()
        }

        composable(route = Screen.REGISTRATION) {
            RegistrationScreen(navController = navController, authViewModel = authViewModel)
        }

        composable(route = Screen.TERMS_AND_CONDITIONS) {
            TermsAndConditionsScreen(navController = navController)
        }
    }
}