package com.alekhin.fovmeapex.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alekhin.fovmeapex.navigation.graph.Graph
import com.alekhin.fovmeapex.navigation.screen.Screen
import com.alekhin.fovmeapex.onboarding.OnBoardingDataStore
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val onBoardingDataStore: OnBoardingDataStore) : ViewModel() {
    val startDestination = mutableStateOf(Screen.ON_BOARDING)
    val loading = mutableStateOf(true)

    init {
        viewModelScope.launch {
            onBoardingDataStore.readOnBoardingState().collect {
                if (it) startDestination.value = Graph.AUTHENTICATION
                else startDestination.value = Screen.ON_BOARDING
            }

            loading.value = false
        }
    }
}