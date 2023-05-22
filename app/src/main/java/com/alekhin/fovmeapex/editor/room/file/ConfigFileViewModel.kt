package com.alekhin.fovmeapex.editor.room.file

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alekhin.fovmeapex.editor.room.ConfigFileOrder
import com.alekhin.fovmeapex.editor.room.ConfigFileUseCase
import com.alekhin.fovmeapex.editor.room.OrderType
import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigFileViewModel @Inject constructor(private val configFileUseCase: ConfigFileUseCase) : ViewModel() {
    private val _state = mutableStateOf(ConfigFileState())
    val state: State<ConfigFileState> = _state

    private var recentlyDeletedConfigFile: ConfigFile? = null

    private var getConfigFilesJob: Job? = null

    init {
        getConfigFiles(ConfigFileOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: ConfigFileEvent) {
        when (event) {
            is ConfigFileEvent.Order -> {
                if (state.value.configFileOrder::class == event.configFileOrder::class && state.value.configFileOrder.orderType == event.configFileOrder.orderType) {
                    return
                }

                getConfigFiles(event.configFileOrder)
            }

            is ConfigFileEvent.DeleteConfigFile -> {
                viewModelScope.launch {
                    configFileUseCase.deleteConfigFile(event.configFile)
                    recentlyDeletedConfigFile = event.configFile
                }
            }

            ConfigFileEvent.RestoreConfigFile -> {
                viewModelScope.launch {
                    configFileUseCase.addConfigFile(recentlyDeletedConfigFile ?: return@launch)
                    recentlyDeletedConfigFile = null
                }
            }
        }
    }

    private fun getConfigFiles(configFileOrder: ConfigFileOrder) {
        getConfigFilesJob?.cancel()
        getConfigFilesJob = configFileUseCase.getConfigFiles(configFileOrder).onEach { configFiles ->
            _state.value = state.value.copy(configFiles = configFiles, configFileOrder = configFileOrder)
        }.launchIn(viewModelScope)
    }
}