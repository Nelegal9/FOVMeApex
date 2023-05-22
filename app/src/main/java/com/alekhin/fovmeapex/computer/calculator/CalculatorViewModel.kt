package com.alekhin.fovmeapex.computer.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

object CalculatorViewModel : ViewModel() {

    // region FIELD OF VIEW

    val fovGame = mutableStateOf(DoubleArray(10))
    val fovScale = mutableStateOf(DoubleArray(10))
    val fov1x1 = mutableStateOf(DoubleArray(10))
    val fov4x3 = mutableStateOf(DoubleArray(10))
    val fov16x9 = mutableStateOf(DoubleArray(10))
    val fovZoom = mutableStateOf(DoubleArray(10))

    // endregion

    // region SENSITIVITY

    val sensGame = mutableStateOf(DoubleArray(10))
    val sensRaw = mutableStateOf(DoubleArray(10))
    val effectiveMouseCPI = mutableStateOf(DoubleArray(10))
    val increment = mutableStateOf(DoubleArray(10))
    val circumference = mutableStateOf(DoubleArray(10))

    // endregion

}