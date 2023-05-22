package com.alekhin.fovmeapex.computer.calculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alekhin.fovmeapex.R
import com.alekhin.fovmeapex.computer.ExpandableTypeList
import com.alekhin.fovmeapex.computer.FovInputType
import com.alekhin.fovmeapex.computer.InputCard
import com.alekhin.fovmeapex.computer.InputTypeDropdownMenuCard
import com.alekhin.fovmeapex.computer.ResultCard
import com.alekhin.fovmeapex.computer.ResultTypeDropdownMenuCard
import com.alekhin.fovmeapex.computer.SensInputType
import com.alekhin.fovmeapex.computer.ZoomedSensInput
import com.alekhin.fovmeapex.ui.theme.Typography

// region MAIN

@Preview(showBackground = true)
@Composable
fun CalculatorScreen() {
    Scaffold(topBar = {
        CalculatorTopBar()
    }) {
        CalculatorContent(paddingValues = it)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CalculatorContent(paddingValues: PaddingValues) {
    HorizontalPager(pageCount = 2, modifier = Modifier.padding(paddingValues = paddingValues)) {
        when (it) {
            0 -> CalculatorInputScreen()
            1 -> CalculatorResultScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalculatorTopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.calculator))
    })
}

// endregion

// region INPUT

// region MAIN

@Composable
@Preview(showBackground = true)
private fun CalculatorInputScreen() {
    CalculatorInputContent()
}

@Composable
private fun CalculatorInputContent() {
    Column {
        CalculatorInputHeader()
        CalculatorInputBody()
    }
}

@Composable
private fun ColumnScope.CalculatorInputHeader() {
    Text(text = "Input", modifier = Modifier.align(CenterHorizontally), fontSize = Typography.headlineSmall.fontSize)
}

private fun getFovCalculations(fovInputType: FovInputType, fovInput: Double) {
    Calculator.calculateFovResults(fovInputType, fovInput)

    CalculatorViewModel.fovGame.value = Calculator.fovGame
    CalculatorViewModel.fovScale.value = Calculator.fovScale
    CalculatorViewModel.fov1x1.value = Calculator.fov1x1
    CalculatorViewModel.fov4x3.value = Calculator.fov4x3
    CalculatorViewModel.fov16x9.value = Calculator.fov16x9
    CalculatorViewModel.fovZoom.value = Calculator.fovZoom
}

private fun getSensCalculations(sensInputType: SensInputType, sensInput: Double, zoomedSensInput: ZoomedSensInput, adsSensInput: Double, mouse_zoomed_sensitivity_scalar_0: Double, mouse_zoomed_sensitivity_scalar_1: Double, mouse_zoomed_sensitivity_scalar_2: Double, mouse_zoomed_sensitivity_scalar_3: Double, mouse_zoomed_sensitivity_scalar_4: Double, mouse_zoomed_sensitivity_scalar_5: Double, mouse_zoomed_sensitivity_scalar_6: Double, mouseCPI: Double) {
    Calculator.calculateSensResults(sensInputType, sensInput, zoomedSensInput, adsSensInput, mouse_zoomed_sensitivity_scalar_0, mouse_zoomed_sensitivity_scalar_1, mouse_zoomed_sensitivity_scalar_2, mouse_zoomed_sensitivity_scalar_3, mouse_zoomed_sensitivity_scalar_4, mouse_zoomed_sensitivity_scalar_5, mouse_zoomed_sensitivity_scalar_6, mouseCPI)

    CalculatorViewModel.sensGame.value = Calculator.sensGame
    CalculatorViewModel.sensRaw.value = Calculator.sensRaw
    CalculatorViewModel.effectiveMouseCPI.value = Calculator.effectiveMouseCPI
    CalculatorViewModel.increment.value = Calculator.increment
    CalculatorViewModel.circumference.value = Calculator.circumference
}

@Composable
private fun CalculatorInputBody() {
    val fovInputType = remember { mutableStateOf(FovInputType.Ingame) }
    val fovInput = remember { mutableStateOf(value = 90.0) }

    getFovCalculations(fovInputType = fovInputType.value, fovInput = fovInput.value)

    val sensInputType = remember { mutableStateOf(SensInputType.Sensitivity) }
    val sensInput = remember { mutableStateOf(value = 5.0) }
    val zoomedSensInput = remember { mutableStateOf(ZoomedSensInput.Basic) }
    val adsSensInput = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_0 = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_1 = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_2 = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_3 = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_4 = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_5 = remember { mutableStateOf(value = 1.0) }
    val mouse_zoomed_sensitivity_scalar_6 = remember { mutableStateOf(value = 1.0) }
    val mouseCPI = remember { mutableStateOf(value = 1600.0) }

    getSensCalculations(sensInputType = sensInputType.value, sensInput = sensInput.value, zoomedSensInput = zoomedSensInput.value, adsSensInput = adsSensInput.value, mouse_zoomed_sensitivity_scalar_0 = mouse_zoomed_sensitivity_scalar_0.value, mouse_zoomed_sensitivity_scalar_1 = mouse_zoomed_sensitivity_scalar_1.value, mouse_zoomed_sensitivity_scalar_2 = mouse_zoomed_sensitivity_scalar_2.value, mouse_zoomed_sensitivity_scalar_3 = mouse_zoomed_sensitivity_scalar_3.value, mouse_zoomed_sensitivity_scalar_4 = mouse_zoomed_sensitivity_scalar_4.value, mouse_zoomed_sensitivity_scalar_5 = mouse_zoomed_sensitivity_scalar_5.value, mouse_zoomed_sensitivity_scalar_6 = mouse_zoomed_sensitivity_scalar_6.value, mouseCPI = mouseCPI.value)

    LazyColumn {
        item {
            CalculatorFieldOfViewInputContent(fovInputType = fovInputType, fovInput = fovInput)
        }

        item {
            CalculatorSensitivityInputContent(sensInputType = sensInputType, sensInput = sensInput, zoomedSensInput = zoomedSensInput, adsSensInput = adsSensInput, mouse_zoomed_sensitivity_scalar_0 = mouse_zoomed_sensitivity_scalar_0, mouse_zoomed_sensitivity_scalar_1 = mouse_zoomed_sensitivity_scalar_1, mouse_zoomed_sensitivity_scalar_2 = mouse_zoomed_sensitivity_scalar_2, mouse_zoomed_sensitivity_scalar_3 = mouse_zoomed_sensitivity_scalar_3, mouse_zoomed_sensitivity_scalar_4 = mouse_zoomed_sensitivity_scalar_4, mouse_zoomed_sensitivity_scalar_5 = mouse_zoomed_sensitivity_scalar_5, mouse_zoomed_sensitivity_scalar_6 = mouse_zoomed_sensitivity_scalar_6, mouseCPI = mouseCPI)
        }
    }
}

// endregion

// region FIELD OF VIEW

private fun fovInputTypeId(fovInputType: FovInputType): Int {
    return when (fovInputType) {
        FovInputType.Ingame -> R.string.field_of_view
        FovInputType.Config -> R.string.cl_fov_scale
        else -> R.string.fov_degrees
    }
}

@Composable
private fun CalculatorFieldOfViewInputContent(fovInputType: MutableState<FovInputType>, fovInput: MutableState<Double>) {
    ExpandableTypeList(text = "Field of View") {
        CalculatorFieldOfViewInput(fovInputType = fovInputType, fovInput = fovInput)
    }
}

@Composable
private fun CalculatorFieldOfViewInput(fovInputType: MutableState<FovInputType>, fovInput: MutableState<Double>) {
    InputTypeDropdownMenuCard(stringId = R.string.field_of_view_input_units, arrayId = R.array.field_of_view_input_units, inputType = fovInputType)
    InputCard(id = fovInputTypeId(fovInputType = fovInputType.value), input = fovInput)
}

// endregion

// region SENSITIVITY

@Composable
private fun CalculatorSensitivityInputContent(sensInputType: MutableState<SensInputType>, sensInput: MutableState<Double>, zoomedSensInput: MutableState<ZoomedSensInput>, adsSensInput: MutableState<Double>, mouse_zoomed_sensitivity_scalar_0: MutableState<Double>, mouse_zoomed_sensitivity_scalar_1: MutableState<Double>, mouse_zoomed_sensitivity_scalar_2: MutableState<Double>, mouse_zoomed_sensitivity_scalar_3: MutableState<Double>, mouse_zoomed_sensitivity_scalar_4: MutableState<Double>, mouse_zoomed_sensitivity_scalar_5: MutableState<Double>, mouse_zoomed_sensitivity_scalar_6: MutableState<Double>, mouseCPI: MutableState<Double>) {
    ExpandableTypeList(text = "Sensitivity") {
        CalculatorMouseCPIInput(mouseCPI = mouseCPI)
        CalculatorHipfireSensitivityInput(sensInputType = sensInputType, sensInput = sensInput)
        CalculatorAdsSensitivityInput(zoomedSensInput = zoomedSensInput, adsSensInput = adsSensInput, mouse_zoomed_sensitivity_scalar_0 = mouse_zoomed_sensitivity_scalar_0, mouse_zoomed_sensitivity_scalar_1 = mouse_zoomed_sensitivity_scalar_1, mouse_zoomed_sensitivity_scalar_2 = mouse_zoomed_sensitivity_scalar_2, mouse_zoomed_sensitivity_scalar_3 = mouse_zoomed_sensitivity_scalar_3, mouse_zoomed_sensitivity_scalar_4 = mouse_zoomed_sensitivity_scalar_4, mouse_zoomed_sensitivity_scalar_5 = mouse_zoomed_sensitivity_scalar_5, mouse_zoomed_sensitivity_scalar_6 = mouse_zoomed_sensitivity_scalar_6)
    }
}

// region CPI

@Composable
private fun CalculatorMouseCPIInput(mouseCPI: MutableState<Double>) {
    InputCard(id = R.string.mouse_counts_per_inch, input = mouseCPI)
}

// endregion

// region HIPFIRE

private fun sensInputTypeId(sensInputType: SensInputType): Int {
    return when (sensInputType) {
        SensInputType.Sensitivity -> R.string.mouse_sensitivity
        SensInputType.Circumference -> R.string.three_sixty_distance_cm_three_sixty
        SensInputType.Increment -> R.string.increment_deg_count
    }
}

@Composable
private fun CalculatorHipfireSensitivityInput(sensInputType: MutableState<SensInputType>, sensInput: MutableState<Double>) {
    InputTypeDropdownMenuCard(stringId = R.string.sensitivity_input_units, arrayId = R.array.sensitivity_input_units, inputType = sensInputType)
    InputCard(id = sensInputTypeId(sensInputType = sensInputType.value), input = sensInput)
}

// endregion

// region ADS

@Composable
private fun CalculatorAdsSensitivityInput(zoomedSensInput: MutableState<ZoomedSensInput>, adsSensInput: MutableState<Double>, mouse_zoomed_sensitivity_scalar_0: MutableState<Double>, mouse_zoomed_sensitivity_scalar_1: MutableState<Double>, mouse_zoomed_sensitivity_scalar_2: MutableState<Double>, mouse_zoomed_sensitivity_scalar_3: MutableState<Double>, mouse_zoomed_sensitivity_scalar_4: MutableState<Double>, mouse_zoomed_sensitivity_scalar_5: MutableState<Double>, mouse_zoomed_sensitivity_scalar_6: MutableState<Double>) {
    InputTypeDropdownMenuCard(stringId = R.string.ads_sensitivity_input_units, arrayId = R.array.ads_sensitivity_input_units, inputType = zoomedSensInput)

    when (zoomedSensInput.value) {
        ZoomedSensInput.Basic -> CalculatorAdsSensitivityMultiplier(adsSensInput = adsSensInput)
        ZoomedSensInput.PerOptic -> CalculatorAdsSensitivityMultiplierPerOptic(mouse_zoomed_sensitivity_scalar_0 = mouse_zoomed_sensitivity_scalar_0, mouse_zoomed_sensitivity_scalar_1 = mouse_zoomed_sensitivity_scalar_1, mouse_zoomed_sensitivity_scalar_2 = mouse_zoomed_sensitivity_scalar_2, mouse_zoomed_sensitivity_scalar_3 = mouse_zoomed_sensitivity_scalar_3, mouse_zoomed_sensitivity_scalar_4 = mouse_zoomed_sensitivity_scalar_4, mouse_zoomed_sensitivity_scalar_5 = mouse_zoomed_sensitivity_scalar_5, mouse_zoomed_sensitivity_scalar_6 = mouse_zoomed_sensitivity_scalar_6)
        else -> {  }
    }
}

@Composable
private fun CalculatorAdsSensitivityMultiplier(adsSensInput: MutableState<Double>) {
    InputCard(id = R.string.ads_mouse_sensitivity_multiplier, input = adsSensInput)
}

@Composable
private fun CalculatorAdsSensitivityMultiplierPerOptic(mouse_zoomed_sensitivity_scalar_0: MutableState<Double>, mouse_zoomed_sensitivity_scalar_1: MutableState<Double>, mouse_zoomed_sensitivity_scalar_2: MutableState<Double>, mouse_zoomed_sensitivity_scalar_3: MutableState<Double>, mouse_zoomed_sensitivity_scalar_4: MutableState<Double>, mouse_zoomed_sensitivity_scalar_5: MutableState<Double>, mouse_zoomed_sensitivity_scalar_6: MutableState<Double>) {
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_0, input = mouse_zoomed_sensitivity_scalar_0)
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_1, input = mouse_zoomed_sensitivity_scalar_1)
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_2, input = mouse_zoomed_sensitivity_scalar_2)
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_3, input = mouse_zoomed_sensitivity_scalar_3)
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_4, input = mouse_zoomed_sensitivity_scalar_4)
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_5, input = mouse_zoomed_sensitivity_scalar_5)
    InputCard(id = R.string.mouse_zoomed_sensitivity_scalar_6, input = mouse_zoomed_sensitivity_scalar_6)
}

// endregion

// endregion

// endregion

// region RESULT

// region MAIN

@Composable
@Preview(showBackground = true)
private fun CalculatorResultScreen() {
    CalculatorResultContent()
}

@Composable
private fun CalculatorResultContent() {
    Column {
        CalculatorResultHeader()
        CalculatorResultBody()
    }
}

@Composable
private fun ColumnScope.CalculatorResultHeader() {
    Text(text = "Result", modifier = Modifier.align(CenterHorizontally), fontSize = Typography.headlineSmall.fontSize)
}

@Composable
private fun CalculatorResultBody() {
    val resultIndex = remember { mutableStateOf(0) }

    LazyColumn {
        item {
            CalculatorAimResultContent(resultIndex = resultIndex)
        }

        item {
            CalculatorFieldOfViewResultContent(resultIndex = resultIndex.value)
        }

        item {
            CalculatorSensitivityResultContent(resultIndex = resultIndex.value)
        }
    }
}

@Composable
private fun CalculatorAimResultContent(resultIndex: MutableState<Int>) {
    CalculatorAimResult(resultIndex = resultIndex)
}

@Composable
private fun CalculatorAimResult(resultIndex: MutableState<Int>) {
    ResultTypeDropdownMenuCard(stringId = R.string.aim, arrayId = R.array.aim, resultIndex = resultIndex)
}

// endregion

// region FIELD OF VIEW

@Composable
private fun CalculatorFieldOfViewResultContent(resultIndex: Int) {
    ExpandableTypeList(text = "Field of View") {
        CalculatorFieldOfViewResult(resultIndex = resultIndex)
    }
}

@Composable
private fun CalculatorFieldOfViewResult(resultIndex: Int) {
    ResultCard(id = R.string.fov_ingame_value, result = CalculatorViewModel.fovGame, index = resultIndex)
    ResultCard(id = R.string.cl_fov_scale, result = CalculatorViewModel.fovScale, index = resultIndex)
    ResultCard(id = R.string.fov_one_by_one, result = CalculatorViewModel.fov1x1, index = resultIndex)
    ResultCard(id = R.string.fov_four_by_three, result = CalculatorViewModel.fov4x3, index = resultIndex)
    ResultCard(id = R.string.fov_sixteen_by_nine, result = CalculatorViewModel.fov16x9, index = resultIndex)
    ResultCard(id = R.string.ads_zoom, result = CalculatorViewModel.fovZoom, index = resultIndex)
}

// endregion

// region SENSITIVITY

@Composable
private fun CalculatorSensitivityResultContent(resultIndex: Int) {
    ExpandableTypeList(text = "Sensitivity") {
        CalculatorSensitivityResult(resultIndex = resultIndex)
    }
}

@Composable
private fun CalculatorSensitivityResult(resultIndex: Int) {
    ResultCard(id = R.string.sensitivity, result = CalculatorViewModel.sensRaw, index = resultIndex)
    ResultCard(id = R.string.label_effective_mouse_counts_per_inch, result = CalculatorViewModel.effectiveMouseCPI, index = resultIndex)
    ResultCard(id = R.string.increment, result = CalculatorViewModel.increment, index = resultIndex)
    ResultCard(id = R.string.circumference_cm_three_sixty, result = CalculatorViewModel.circumference, index = resultIndex)
    ResultCard(id = R.string.ads_multiplier, result = CalculatorViewModel.sensGame, index = resultIndex)
}

// endregion

// endregion