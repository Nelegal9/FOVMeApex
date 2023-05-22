package com.alekhin.fovmeapex.editor.room.file

import com.alekhin.fovmeapex.editor.room.ConfigFileOrder
import com.alekhin.fovmeapex.editor.room.database.ConfigFile

sealed class ConfigFileEvent {
    data class Order(val configFileOrder: ConfigFileOrder): ConfigFileEvent()
    data class DeleteConfigFile(val configFile: ConfigFile): ConfigFileEvent()
    object RestoreConfigFile: ConfigFileEvent()
}