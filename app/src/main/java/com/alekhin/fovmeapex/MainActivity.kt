package com.alekhin.fovmeapex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.navigation.graph.RootNavGraph
import com.alekhin.fovmeapex.splash.SplashViewModel
import com.alekhin.fovmeapex.ui.theme.FOVMeApexTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { !splashViewModel.loading.value }
        setContent {
            FOVMeApexTheme {
                FOVMeApexApp(splashViewModel = splashViewModel, authViewModel = authViewModel)
            }
        }
    }
}

@Composable
private fun FOVMeApexApp(splashViewModel: SplashViewModel, authViewModel: AuthViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        RootNavGraph(navController = rememberNavController(), startDestination = splashViewModel.startDestination.value, authViewModel = authViewModel)
    }
}