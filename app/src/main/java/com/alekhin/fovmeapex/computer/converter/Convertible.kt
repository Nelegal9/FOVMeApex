package com.alekhin.fovmeapex.computer.converter

import com.alekhin.fovmeapex.computer.FovInputType
import com.alekhin.fovmeapex.computer.SensInputType

interface Convertible {

    // region FIELD OF VIEW

    // region VARIABLES

    val fovGame: DoubleArray
    val fovScale: DoubleArray
    val fov1x1: DoubleArray
    val fov4x3: DoubleArray
    val fov16x9: DoubleArray
    val fovZoom: DoubleArray

    // endregion

    // region FUNCTIONS

    fun convertFovResults(converterFovInputType: FovInputType, converterFovInputOld: Double, converterFovInputNew: Double)

    // endregion

    // endregion

    // region SENSITIVITY

    // region VARIABLES

    val sensYaw: DoubleArray
    val sensGame: DoubleArray
    val sensRaw: DoubleArray
    val effectiveMouseCPI: DoubleArray
    val increment: DoubleArray
    val circumference: DoubleArray

    // endregion

    // region FUNCTIONS

    fun convertSensResults(converterSensInputType: SensInputType, converterSensInputOld: Double, mouseCPI: Double)

    // endregion

    // endregion

}