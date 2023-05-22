package com.alekhin.fovmeapex.editor.room.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alekhin.fovmeapex.editor.room.ConfigFileUseCase
import com.alekhin.fovmeapex.editor.room.InvalidConfigFileException
import com.alekhin.fovmeapex.editor.room.UIEvent
import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditConfigFileViewModel @Inject constructor(private val configFileUseCase: ConfigFileUseCase, savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _configFileName = mutableStateOf(ConfigFileTextFieldState(hint = "Enter name of the config file..."))
    val configFileName: State<ConfigFileTextFieldState> = _configFileName
    private val _configFileContent = mutableStateOf(ConfigFileTextFieldState(hint = "Enter contents of the config file..."))
    val configFileContent: State<ConfigFileTextFieldState> = _configFileContent

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFLow = _eventFlow.asSharedFlow()

    private var currentConfigFileId: Int? = null

    init {
        savedStateHandle.get<Int>("configFileId")?.let { configFileId ->
            if (configFileId != -1) {
                viewModelScope.launch {
                    configFileUseCase.getConfigFile(configFileId)?.also { configFile ->
                        currentConfigFileId = configFile.id
                        _configFileName.value = configFileName.value.copy(text = configFile.name, visible = false)
                        _configFileContent.value = configFileContent.value.copy(text = configFile.content, visible = false)
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditConfigFileEvent) {
        when (event) {
            is AddEditConfigFileEvent.EnteredName -> _configFileName.value = configFileName.value.copy(text = event.value)
            is AddEditConfigFileEvent.ChangeNameFocus -> _configFileName.value = configFileName.value.copy(visible = !event.focusState.isFocused)
            is AddEditConfigFileEvent.EnteredContent -> _configFileContent.value = configFileContent.value.copy(text = event.value)
            is AddEditConfigFileEvent.ChangeContentFocus -> _configFileContent.value = configFileContent.value.copy(visible = !event.focusState.isFocused)
            AddEditConfigFileEvent.SaveConfigFile -> viewModelScope.launch {
                try {
                    configFileUseCase.addConfigFile(
                        ConfigFile(
                            id = currentConfigFileId,
                            name = configFileName.value.text,
                            content = configFileContent.value.text,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                    _eventFlow.emit(UIEvent.SaveConfigFile)
                } catch (invalidConfigFileException: InvalidConfigFileException) {
                    _eventFlow.emit(
                        UIEvent.ShowSnackbar(
                            message = invalidConfigFileException.message ?: "Couldn't save note."
                        )
                    )
                }
            }
        }
    }
}