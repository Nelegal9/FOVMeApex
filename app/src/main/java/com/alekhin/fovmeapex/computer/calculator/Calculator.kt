package com.alekhin.fovmeapex.computer.calculator

import com.alekhin.fovmeapex.computer.Computer
import com.alekhin.fovmeapex.computer.FovInputType
import com.alekhin.fovmeapex.computer.SensInputType
import com.alekhin.fovmeapex.computer.ZoomedSensInput

object Calculator : Computer(), Calculable {

    // region FIELD OF VIEW

    // region VARIABLES

    override val fovGame = DoubleArray(10)
    override val fovScale = DoubleArray(10)
    override val fov1x1 = DoubleArray(10)
    override val fov4x3 = DoubleArray(10)
    override val fov16x9 = DoubleArray(10)
    override val fovZoom = DoubleArray(10)

    // endregion

    // region FUNCTIONS

    private fun calculateHipfireFov(fovInputType: FovInputType, fovInput: Double) {
        when (fovInputType) {
            FovInputType.Ingame -> {
                fovGame[0] = fovInput
                fovScale[0] = convertFovIngameToScale(fovGame[0])
            }

            FovInputType.Config -> {
                fovScale[0] = fovInput
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
            }

            FovInputType.Degrees1x1 -> {
                fovScale[0] = convertFovDegreesToScale(fovInput, 1.0)
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
            }

            FovInputType.Degrees4x3 -> {
                fovScale[0] = convertFovDegreesToScale(fovInput, 4.0 / 3.0)
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
            }

            FovInputType.Degrees16x9 -> {
                fovScale[0] = convertFovDegreesToScale(fovInput, 16.0 / 9.0)
                fovGame[0] = convertFovScaleToIngame(fovScale[0])
            }
        }

        fov1x1[0] = convertFovScaleToDegrees(fovScale[0], 1.0)
        fov4x3[0] = convertFovScaleToDegrees(fovScale[0], 4.0 / 3.0)
        fov16x9[0] = convertFovScaleToDegrees(fovScale[0], 16.0 / 9.0)
        fovZoom[0] = 1.0
    }

    private fun calculateAdsFov() {
        fov4x3[1] = fov4x3[0] * 60.0 / 70.0
        fov4x3[2] = fov4x3[0] * 55.0 / 70.0
        fov4x3[3] = fov4x3[1]
        fov4x3[4] = fov4x3[0] * adsFovMultiplier(2.0)
        fov4x3[5] = fov4x3[0] * adsFovMultiplier(3.0)
        fov4x3[6] = fov4x3[0] * adsFovMultiplier(4.0)
        fov4x3[7] = fov4x3[0] * adsFovMultiplier(6.0)
        fov4x3[8] = fov4x3[0] * adsFovMultiplier(8.0)
        fov4x3[9] = fov4x3[0] * adsFovMultiplier(10.0)

        for (k in 1..9) {
            fovGame[k] = convertFovDegreesToIngame(fov4x3[k], 4.0 / 3.0)
            fovScale[k] = convertFovDegreesToScale(fov4x3[k], 4.0 / 3.0)
            fov1x1[k] = convertFovDegrees(fov4x3[k], 4.0 / 3.0, 1.0)
            fov16x9[k] = convertFovDegrees(fov4x3[k], 4.0 / 3.0, 16.0 / 9.0)
            fovZoom[k] = zoom(fov4x3[0], fov4x3[k])
        }
    }

    // endregion

    // region CALCULATIONS

    override fun calculateFovResults(fovInputType: FovInputType, fovInput: Double) {
        calculateHipfireFov(fovInputType, fovInput)
        calculateAdsFov()
    }

    // endregion

    // endregion

    // region SENSITIVITY

    // region VARIABLES

    override val sensYaw = DoubleArray(10)
    override val sensGame = DoubleArray(10)
    override val sensRaw = DoubleArray(10)
    override val effectiveMouseCPI = DoubleArray(10)
    override val increment = DoubleArray(10)
    override val circumference = DoubleArray(10)

    // endregion

    // region FUNCTIONS

    private fun calculateHipfireSens(sensInputType: SensInputType, sensInput: Double, mouseCPI: Double) {
        when (sensInputType) {
            SensInputType.Sensitivity -> {
                sensYaw[0] = 0.022
                sensGame[0] = sensInput
                sensRaw[0] = sensGame[0]
                effectiveMouseCPI[0] = effectiveMouseCPI(sensRaw[0], mouseCPI)
                increment[0] = increment(sensRaw[0])
                circumference[0] = circumference(sensRaw[0], mouseCPI)
            }

            SensInputType.Circumference -> {
                sensYaw[0] = 0.022
                circumference[0] = sensInput
                sensGame[0] = circumferenceToSensitivity(circumference[0], sensYaw[0], mouseCPI)
                sensRaw[0] = sensGame[0]
                effectiveMouseCPI[0] = effectiveMouseCPI(sensRaw[0], mouseCPI)
                increment[0] = increment(sensRaw[0])
            }

            SensInputType.Increment -> {
                sensYaw[0] = 0.022
                increment[0] = sensInput
                sensGame[0] = increment[0] / sensYaw[0]
                sensRaw[0] = sensGame[0]
                effectiveMouseCPI[0] = effectiveMouseCPI(sensRaw[0], mouseCPI)
                circumference[0] = circumference(sensRaw[0], mouseCPI)
            }
        }
    }

    private fun calculateAdsSens(zoomedSensInput: ZoomedSensInput, adsSensInput: Double, mouse_zoomed_sensitivity_scalar_0: Double, mouse_zoomed_sensitivity_scalar_1: Double, mouse_zoomed_sensitivity_scalar_2: Double, mouse_zoomed_sensitivity_scalar_3: Double, mouse_zoomed_sensitivity_scalar_4: Double, mouse_zoomed_sensitivity_scalar_5: Double, mouse_zoomed_sensitivity_scalar_6: Double, mouseCPI: Double) {
        for (k in 1..9) {
            sensYaw[k] = 0.022 * sensGame[0] * adsSensMultiplier(fov4x3[0], fov4x3[k])

            when (zoomedSensInput) {
                ZoomedSensInput.Basic -> sensGame[k] = adsSensInput
                ZoomedSensInput.PerOptic -> {
                    sensGame[1] = mouse_zoomed_sensitivity_scalar_0
                    sensGame[2] = mouse_zoomed_sensitivity_scalar_0
                    sensGame[3] = mouse_zoomed_sensitivity_scalar_0
                    sensGame[4] = mouse_zoomed_sensitivity_scalar_1
                    sensGame[5] = mouse_zoomed_sensitivity_scalar_2
                    sensGame[6] = mouse_zoomed_sensitivity_scalar_3
                    sensGame[7] = mouse_zoomed_sensitivity_scalar_4
                    sensGame[8] = mouse_zoomed_sensitivity_scalar_5
                    sensGame[9] = mouse_zoomed_sensitivity_scalar_6
                }

                ZoomedSensInput.Auto -> sensGame[k] = 1.0 / (adsSensMultiplier(fov4x3[0], fov4x3[k]) * zoom(fov4x3[0], fov4x3[k]))
                ZoomedSensInput.MatchCircumference -> sensGame[k] = 1.0 / adsSensMultiplier(fov4x3[0], fov4x3[k])
            }

            sensRaw[k] = (sensGame[k] * sensYaw[k]) / sensYaw[0]
            effectiveMouseCPI[k] = effectiveMouseCPI(sensRaw[k], mouseCPI)
            increment[k] = increment(sensRaw[k])
            circumference[k] = circumference(sensRaw[k], mouseCPI)
        }
    }

    // endregion

    // region CALCULATIONS

    override fun calculateSensResults(sensInputType: SensInputType, sensInput: Double, zoomedSensInput: ZoomedSensInput, adsSensInput: Double, mouse_zoomed_sensitivity_scalar_0: Double, mouse_zoomed_sensitivity_scalar_1: Double, mouse_zoomed_sensitivity_scalar_2: Double, mouse_zoomed_sensitivity_scalar_3: Double, mouse_zoomed_sensitivity_scalar_4: Double, mouse_zoomed_sensitivity_scalar_5: Double, mouse_zoomed_sensitivity_scalar_6: Double, mouseCPI: Double) {
        calculateHipfireSens(sensInputType, sensInput, mouseCPI)
        calculateAdsSens(zoomedSensInput, adsSensInput, mouse_zoomed_sensitivity_scalar_0, mouse_zoomed_sensitivity_scalar_1, mouse_zoomed_sensitivity_scalar_2, mouse_zoomed_sensitivity_scalar_3, mouse_zoomed_sensitivity_scalar_4, mouse_zoomed_sensitivity_scalar_5, mouse_zoomed_sensitivity_scalar_6, mouseCPI)
    }

    // endregion

    // endregion

}