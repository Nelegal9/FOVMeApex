package com.alekhin.fovmeapex.computer.converter

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
import com.alekhin.fovmeapex.ui.theme.Typography

// region MAIN

@Preview(showBackground = true)
@Composable
fun ConverterScreen() {
    Scaffold(topBar = {
        ConverterTopBar()
    }) {
        ConverterContent(paddingValues = it)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ConverterContent(paddingValues: PaddingValues) {
    HorizontalPager(pageCount = 2, modifier = Modifier.padding(paddingValues = paddingValues)) {
        when (it) {
            0 -> ConverterInputScreen()
            1 -> ConverterResultScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConverterTopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.sensitivity_converter))
    })
}

// endregion

// region INPUT

// region MAIN

@Composable
@Preview(showBackground = true)
private fun ConverterInputScreen() {
    ConverterInputContent()
}

@Composable
private fun ConverterInputContent() {
    Column {
        ConverterInputHeader()
        ConverterInputBody()
    }
}

@Composable
private fun ColumnScope.ConverterInputHeader() {
    Text(text = "Input", modifier = Modifier.align(CenterHorizontally), fontSize = Typography.headlineSmall.fontSize)
}

private fun getFovConversions(converterFovInputType: FovInputType, converterFovInputOld: Double, converterFovInputNew: Double) {
    Converter.convertFovResults(converterFovInputType = converterFovInputType, converterFovInputOld = converterFovInputOld, converterFovInputNew = converterFovInputNew)

    ConverterViewModel.fovGame.value = Converter.fovGame
    ConverterViewModel.fovScale.value = Converter.fovScale
    ConverterViewModel.fov1x1.value = Converter.fov1x1
    ConverterViewModel.fov4x3.value = Converter.fov4x3
    ConverterViewModel.fov16x9.value = Converter.fov16x9
    ConverterViewModel.fovZoom.value = Converter.fovZoom
}

private fun getSensConversions(converterSensInputType: SensInputType, converterSensInputOld: Double, mouseCPI: Double) {
    Converter.convertSensResults(converterSensInputType = converterSensInputType, converterSensInputOld = converterSensInputOld, mouseCPI = mouseCPI)

    ConverterViewModel.sensGame.value = Converter.sensGame
    ConverterViewModel.sensRaw.value = Converter.sensRaw
    ConverterViewModel.effectiveMouseCPI.value = Converter.effectiveMouseCPI
    ConverterViewModel.increment.value = Converter.increment
    ConverterViewModel.circumference.value = Converter.circumference
}

@Composable
private fun ConverterInputBody() {
    val converterFovInputType = remember { mutableStateOf(FovInputType.Ingame) }
    val converterFovInputOld = remember { mutableStateOf(90.0) }
    val converterFovInputNew = remember { mutableStateOf(110.0) }

    getFovConversions(converterFovInputType = converterFovInputType.value, converterFovInputOld = converterFovInputOld.value, converterFovInputNew = converterFovInputNew.value)

    val converterSensInputType = remember { mutableStateOf(SensInputType.Sensitivity) }
    val converterSensInputOld = remember { mutableStateOf(5.0) }
    val mouseCPI = remember { mutableStateOf(1600.0) }

    getSensConversions(converterSensInputType = converterSensInputType.value, converterSensInputOld = converterSensInputOld.value, mouseCPI = mouseCPI.value)

    LazyColumn {
        item {
            ConverterFieldOfViewInputContent(converterFovInputType = converterFovInputType, converterFovInputOld = converterFovInputOld, converterFovInputNew = converterFovInputNew)
        }

        item {
            ConverterSensitivityInputContent(converterSensInputType = converterSensInputType, converterSensInputOld = converterSensInputOld, mouseCPI = mouseCPI)
        }
    }
}

// endregion

// region FIELD OF VIEW

private fun fovInputTypeIdOld(converterFovInputType: FovInputType): Int {
    return when (converterFovInputType) {
        FovInputType.Ingame -> R.string.old_field_of_view
        FovInputType.Config -> R.string.old_cl_fov_scale
        else -> R.string.old_fov_degrees
    }
}

private fun fovInputTypeIdNew(converterFovInputType: FovInputType): Int {
    return when (converterFovInputType) {
        FovInputType.Ingame -> R.string.new_field_of_view
        FovInputType.Config -> R.string.new_cl_fov_scale
        else -> R.string.new_fov_degrees
    }
}

@Composable
private fun ConverterFieldOfViewInputContent(converterFovInputType: MutableState<FovInputType>, converterFovInputOld: MutableState<Double>, converterFovInputNew: MutableState<Double>) {
    ExpandableTypeList(text = "Field of View") {
        ConverterFieldOfViewInput(converterFovInputType = converterFovInputType, converterFovInputOld = converterFovInputOld, converterFovInputNew = converterFovInputNew)
    }
}

@Composable
private fun ConverterFieldOfViewInput(converterFovInputType: MutableState<FovInputType>, converterFovInputOld: MutableState<Double>, converterFovInputNew: MutableState<Double>) {
    InputTypeDropdownMenuCard(stringId = R.string.field_of_view_input_units, arrayId = R.array.field_of_view_input_units, inputType = converterFovInputType)
    InputCard(id = fovInputTypeIdOld(converterFovInputType = converterFovInputType.value), input = converterFovInputOld)
    InputCard(id = fovInputTypeIdNew(converterFovInputType = converterFovInputType.value), input = converterFovInputNew)
}

// endregion

// region SENSITIVITY

@Composable
private fun ConverterSensitivityInputContent(converterSensInputType: MutableState<SensInputType>, converterSensInputOld: MutableState<Double>, mouseCPI: MutableState<Double>) {
    ExpandableTypeList(text = "Sensitivity") {
        ConverterMouseCPIInput(mouseCPI = mouseCPI)
        ConverterHipfireSensitivityInput(converterSensInputType = converterSensInputType, sensInput = converterSensInputOld)
    }
}

// region CPI

@Composable
private fun ConverterMouseCPIInput(mouseCPI: MutableState<Double>) {
    InputCard(id = R.string.mouse_counts_per_inch, input = mouseCPI)
}

// endregion

// region HIPFIRE

private fun sensInputTypeId(converterSensInputType: SensInputType): Int {
    return when (converterSensInputType) {
        SensInputType.Sensitivity -> R.string.mouse_sensitivity
        SensInputType.Circumference -> R.string.three_sixty_distance_cm_three_sixty
        SensInputType.Increment -> R.string.increment_deg_count
    }
}

@Composable
private fun ConverterHipfireSensitivityInput(converterSensInputType: MutableState<SensInputType>, sensInput: MutableState<Double>) {
    InputTypeDropdownMenuCard(stringId = R.string.sensitivity_input_units, arrayId = R.array.sensitivity_input_units, inputType = converterSensInputType)
    InputCard(id = sensInputTypeId(converterSensInputType = converterSensInputType.value), input = sensInput)
}

// endregion

// endregion

// endregion

// region RESULT

// region MAIN

@Composable
@Preview(showBackground = true)
private fun ConverterResultScreen() {
    ConverterResultContent()
}

@Composable
private fun ConverterResultContent() {
    Column {
        ConverterResultHeader()
        ConverterResultBody()
    }
}

@Composable
private fun ColumnScope.ConverterResultHeader() {
    Text(text = "Result", modifier = Modifier.align(CenterHorizontally), fontSize = Typography.headlineSmall.fontSize)
}

@Composable
private fun ConverterResultBody() {
    val resultIndex = remember { mutableStateOf(0) }

    LazyColumn {
        item {
            ConverterAimResultContent(resultIndex = resultIndex)
        }

        item {
            ConverterFieldOfViewResultContent(resultIndex = resultIndex.value)
        }

        item {
            ConverterSensitivityResultContent(resultIndex = resultIndex.value)
        }
    }
}

@Composable
private fun ConverterAimResultContent(resultIndex: MutableState<Int>) {
    ConverterAimResult(resultIndex = resultIndex)
}

@Composable
private fun ConverterAimResult(resultIndex: MutableState<Int>) {
    ResultTypeDropdownMenuCard(stringId = R.string.aim, arrayId = R.array.converter_aim, resultIndex = resultIndex)
}

// endregion

// region FIELD OF VIEW

@Composable
private fun ConverterFieldOfViewResultContent(resultIndex: Int) {
    ExpandableTypeList(text = "Field of View") {
        ConverterFieldOfViewResult(resultIndex = resultIndex)
    }
}

@Composable
private fun ConverterFieldOfViewResult(resultIndex: Int) {
    ResultCard(id = R.string.fov_ingame_value, result = ConverterViewModel.fovGame, index = resultIndex)
    ResultCard(id = R.string.cl_fov_scale, result = ConverterViewModel.fovScale, index = resultIndex)
    ResultCard(id = R.string.fov_one_by_one, result = ConverterViewModel.fov1x1, index = resultIndex)
    ResultCard(id = R.string.fov_four_by_three, result = ConverterViewModel.fov4x3, index = resultIndex)
    ResultCard(id = R.string.fov_sixteen_by_nine, result = ConverterViewModel.fov16x9, index = resultIndex)
    ResultCard(id = R.string.ads_zoom, result = ConverterViewModel.fovZoom, index = resultIndex)
}

// endregion

// region SENSITIVITY

@Composable
private fun ConverterSensitivityResultContent(resultIndex: Int) {
    ExpandableTypeList(text = "Sensitivity") {
        ConverterSensitivityResult(resultIndex = resultIndex)
    }
}

@Composable
private fun ConverterSensitivityResult(resultIndex: Int) {
    ResultCard(id = R.string.sensitivity, result = ConverterViewModel.sensRaw, index = resultIndex)
    ResultCard(id = R.string.label_effective_mouse_counts_per_inch, result = ConverterViewModel.effectiveMouseCPI, index = resultIndex)
    ResultCard(id = R.string.increment, result = ConverterViewModel.increment, index = resultIndex)
    ResultCard(id = R.string.circumference_cm_three_sixty, result = ConverterViewModel.circumference, index = resultIndex)
    ResultCard(id = R.string.ads_multiplier, result = ConverterViewModel.sensGame, index = resultIndex)
}

// endregion

// endregion