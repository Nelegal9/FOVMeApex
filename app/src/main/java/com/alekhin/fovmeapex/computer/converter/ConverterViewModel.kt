package com.alekhin.fovmeapex.computer.converter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

object ConverterViewModel : ViewModel() {

    // region FIELD OF VIEW

    val fovGame = mutableStateOf(DoubleArray(2))
    val fovScale = mutableStateOf(DoubleArray(2))
    val fov1x1 = mutableStateOf(DoubleArray(2))
    val fov4x3 = mutableStateOf(DoubleArray(2))
    val fov16x9 = mutableStateOf(DoubleArray(2))
    val fovZoom = mutableStateOf(DoubleArray(2))

    // endregion

    // region SENSITIVITY

    val sensGame = mutableStateOf(DoubleArray(2))
    val sensRaw = mutableStateOf(DoubleArray(2))
    val effectiveMouseCPI = mutableStateOf(DoubleArray(2))
    val increment = mutableStateOf(DoubleArray(2))
    val circumference = mutableStateOf(DoubleArray(2))

    // endregion

}