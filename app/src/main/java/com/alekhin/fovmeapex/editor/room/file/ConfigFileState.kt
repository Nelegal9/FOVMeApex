package com.alekhin.fovmeapex.editor.room.file

import com.alekhin.fovmeapex.editor.room.ConfigFileOrder
import com.alekhin.fovmeapex.editor.room.OrderType
import com.alekhin.fovmeapex.editor.room.database.ConfigFile

data class ConfigFileState(
    val configFiles: List<ConfigFile> = emptyList(),
    val configFileOrder: ConfigFileOrder = ConfigFileOrder.Date(OrderType.Descending)
)