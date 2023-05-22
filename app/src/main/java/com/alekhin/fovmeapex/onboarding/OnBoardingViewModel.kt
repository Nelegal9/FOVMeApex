package com.alekhin.fovmeapex.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardingDataStore: OnBoardingDataStore) : ViewModel() {
    fun saveOnBoardingState() {
        viewModelScope.launch {
            onBoardingDataStore.saveOnBoardingState()
        }
    }
}