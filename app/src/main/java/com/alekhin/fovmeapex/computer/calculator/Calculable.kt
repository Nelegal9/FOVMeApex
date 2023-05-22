package com.alekhin.fovmeapex.computer.calculator

import com.alekhin.fovmeapex.computer.FovInputType
import com.alekhin.fovmeapex.computer.SensInputType
import com.alekhin.fovmeapex.computer.ZoomedSensInput

interface Calculable {

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

    fun calculateFovResults(fovInputType: FovInputType, fovInput: Double)

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

    fun calculateSensResults(sensInputType: SensInputType, sensInput: Double, zoomedSensInput: ZoomedSensInput, adsSensInput: Double, mouse_zoomed_sensitivity_scalar_0: Double, mouse_zoomed_sensitivity_scalar_1: Double, mouse_zoomed_sensitivity_scalar_2: Double, mouse_zoomed_sensitivity_scalar_3: Double, mouse_zoomed_sensitivity_scalar_4: Double, mouse_zoomed_sensitivity_scalar_5: Double, mouse_zoomed_sensitivity_scalar_6: Double, mouseCPI: Double)

    // endregion

    // endregion

}