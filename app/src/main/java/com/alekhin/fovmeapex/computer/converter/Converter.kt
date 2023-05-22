package com.alekhin.fovmeapex.computer.converter

import com.alekhin.fovmeapex.computer.Computer
import com.alekhin.fovmeapex.computer.FovInputType
import com.alekhin.fovmeapex.computer.SensInputType

object Converter : Computer(), Convertible {

    // region FIELD OF VIEW

    // region VARIABLES

    override val fovGame = DoubleArray(2)
    override val fovScale = DoubleArray(2)
    override val fov1x1 = DoubleArray(2)
    override val fov4x3 = DoubleArray(2)
    override val fov16x9 = DoubleArray(2)
    override val fovZoom = DoubleArray(2)

    // endregion

    // region FUNCTIONS

    private fun convertFov(converterFovInputType: FovInputType, converterFovInputOld: Double, converterFovInputNew: Double) {
        when (converterFovInputType) {
            FovInputType.Ingame -> {
                fovGame[0] = converterFovInputOld
                fovScale[0] = convertFovIngameToScale(fovGame[0])
                fovGame[1] = converterFovInputNew
                fovScale[1] = convertFovIngameToScale(fovGame[1])
            }

            FovInputType.Config -> {
                fovScale[0] = converterFovInputOld
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
                fovScale[1] = converterFovInputNew
                fovGame[1] = convertFovScaleToIngame(fovScale[1])
            }

            FovInputType.Degrees1x1 -> {
                fovScale[0] = convertFovDegreesToScale(converterFovInputOld, 1.0)
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
                fovScale[1] = convertFovDegreesToScale(converterFovInputNew, 1.0)
                fovGame[1] = convertFovScaleToIngame(fovScale[1])
            }

            FovInputType.Degrees4x3 -> {
                fovScale[0] = convertFovDegreesToScale(converterFovInputOld, 4.0 / 3.0)
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
                fovScale[1] = convertFovDegreesToScale(converterFovInputNew, 4.0 / 3.0)
                fovGame[1] = convertFovScaleToIngame(fovScale[1])
            }

            FovInputType.Degrees16x9 -> {
                fovScale[0] = convertFovDegreesToScale(converterFovInputOld, 16.0 / 9.0)
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
                fovScale[1] = convertFovDegreesToScale(converterFovInputNew, 16.0 / 9.0)
                fovGame[1] = convertFovScaleToIngame(fovScale[1])
            }
        }

        fov1x1[0] = convertFovScaleToDegrees(fovScale[0], 1.0)
        fov4x3[0] = convertFovScaleToDegrees(fovScale[0], 4.0 / 3.0)
        fov16x9[0] = convertFovScaleToDegrees(fovScale[0], 16.0 / 9.0)
        fovZoom[0] = 1.0

        fov1x1[1] = convertFovScaleToDegrees(fovScale[1], 1.0)
        fov4x3[1] = convertFovScaleToDegrees(fovScale[1], 4.0 / 3.0)
        fov16x9[1] = convertFovScaleToDegrees(fovScale[1], 16.0 / 9.0)
        fovZoom[1] = 1.0
    }

    // endregion

    // region CONVERSIONS

    override fun convertFovResults(converterFovInputType: FovInputType, converterFovInputOld: Double, converterFovInputNew: Double) {
        convertFov(converterFovInputType, converterFovInputOld, converterFovInputNew)
    }

    // endregion

    // endregion

    // region SENSITIVITY

    // region VARIABLES

    override val sensYaw = DoubleArray(2)
    override val sensGame = DoubleArray(2)
    override val sensRaw = DoubleArray(2)
    override val effectiveMouseCPI = DoubleArray(2)
    override val increment = DoubleArray(2)
    override val circumference = DoubleArray(2)

    // endregion

    // region FUNCTIONS

    private fun convertSens(converterSensInputType: SensInputType, converterSensInputOld: Double, mouseCPI: Double) {
        when (converterSensInputType) {
            SensInputType.Sensitivity -> {
                sensYaw[0] = 0.022
                sensGame[0] = converterSensInputOld
                sensRaw[0] = sensGame[0]
                effectiveMouseCPI[0] = effectiveMouseCPI(sensRaw[0], mouseCPI)
                increment[0] = increment(sensRaw[0])
                circumference[0] = circumference(sensRaw[0], mouseCPI)
            }

            SensInputType.Circumference -> {
                sensYaw[0] = 0.022
                circumference[0] = converterSensInputOld
                sensGame[0] = circumferenceToSensitivity(circumference[0], sensYaw[0], mouseCPI)
                sensRaw[0] = sensGame[0]
                effectiveMouseCPI[0] = effectiveMouseCPI(sensRaw[0], mouseCPI)
                increment[0] = increment(sensRaw[0])
            }

            SensInputType.Increment -> {
                sensYaw[0] = 0.022
                increment[0] = converterSensInputOld
                sensGame[0] = increment[0] / sensYaw[0]
                sensRaw[0] = sensGame[0]
                effectiveMouseCPI[0] = effectiveMouseCPI(sensRaw[0], mouseCPI)
                circumference[0] = circumference(sensRaw[0], mouseCPI)
            }
        }

        sensYaw[1] = sensYaw[0] * zoom(fov4x3[0], fov4x3[1])
        sensGame[1] = sensGame[0] * sensYaw[0] / sensYaw[1]
        sensRaw[1] = sensGame[1]
        effectiveMouseCPI[1] = effectiveMouseCPI(sensRaw[1], mouseCPI)
        increment[1] = increment(sensRaw[1])
        circumference[1] = circumference(sensRaw[1], mouseCPI)
    }

    // endregion

    // region CONVERSIONS

    override fun convertSensResults(converterSensInputType: SensInputType, converterSensInputOld: Double, mouseCPI: Double) {
        convertSens(converterSensInputType, converterSensInputOld, mouseCPI)
    }

    // endregion

    // endregion

}