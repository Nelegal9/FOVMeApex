package com.alekhin.fovmeapex.computer

import kotlin.math.atan
import kotlin.math.roundToInt
import kotlin.math.tan

abstract class Computer {

    // region FIELD OF VIEW

    private fun convertFovIngameToDegrees(fov: Double, measurement: Double): Double {
        return (360.0 * atan(measurement * tan((70.0 * (1.0 + ((fov - 70.0) * 0.01375))) * Math.PI / 360.0) / (4.0 / 3.0))) / Math.PI
    }

    protected fun convertFovIngameToScale(fov: Double): Double {
        return convertFovIngameToDegrees(fov, 4.0 / 3.0) / 70.0
    }

    protected fun convertFovDegreesToIngame(degrees: Double, measurement: Double): Double {
        return (((((360.0 * atan(4.0 / 3.0 * tan(degrees * Math.PI / 360.0) / measurement)) / Math.PI) / 70.0) / 0.01375) - (1.0 / 0.01375)) + 70.0
    }

    protected fun convertFovScaleToDegrees(scale: Double, measurement: Double): Double {
        return ((360.0 * atan((measurement * tan((70.0 * scale) * Math.PI / 360.0)) / (4.0 / 3.0))) / Math.PI)
    }

    protected fun convertFovScaleToIngame(scale: Double): Double {
        return convertFovDegreesToIngame(convertFovScaleToDegrees(scale, 4.0 / 3.0), 4.0 / 3.0)
    }

    protected fun convertFovDegreesToScale(degrees: Double, measurement: Double): Double {
        return ((360.0 * atan((4.0 / 3.0 * tan(degrees * Math.PI / 360.0)) / measurement)) / Math.PI) / 70.0
    }

    protected fun adsFovMultiplier(multiplier: Double): Double {
        return ((360.0 * atan(tan((70.0 * Math.PI / 360.0)) / multiplier)) / Math.PI) / 70.0
    }

    protected fun convertFovDegrees(degrees: Double, measurement1: Double, measurement2: Double): Double {
        return (360.0 * atan(measurement2 * tan(degrees * Math.PI / 360.0) / measurement1)) / Math.PI
    }

    protected fun zoom(hipfire: Double, ads: Double): Double {
        return tan(hipfire * Math.PI / 360.0) / tan(ads * Math.PI / 360.0)
    }

    // endregion

    // region SENSITIVITY

    protected fun effectiveMouseCPI(sensitivity: Double, mouseCPI: Double): Double {
        return sensitivity * mouseCPI
    }

    protected fun increment(sensitivity: Double): Double {
        return 0.022 * sensitivity
    }

    protected fun circumference(sensitivity: Double, mouseCPI: Double): Double {
        return 360.0 / (increment(sensitivity) * mouseCPI / 2.54)
    }

    protected fun circumferenceToSensitivity(circumference: Double, yaw: Double, mouseCPI: Double): Double {
        return 360.0 / (circumference * yaw * mouseCPI / 2.54)
    }

    protected fun adsSensMultiplier(hipfire: Double, ads: Double): Double {
        return tan(ads * Math.PI / 360.0) / tan(hipfire.roundToInt() * Math.PI / 360.0)
    }
}

// endregion