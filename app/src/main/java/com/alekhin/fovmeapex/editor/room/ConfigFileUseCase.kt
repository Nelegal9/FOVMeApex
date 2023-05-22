package com.alekhin.fovmeapex.editor.room

data class ConfigFileUseCase(
    val getConfigFiles: GetConfigFiles,
    val deleteConfigFile: DeleteConfigFile,
    val addConfigFile: AddConfigFile,
    val getConfigFile: GetConfigFile
)