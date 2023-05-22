package com.alekhin.fovmeapex.editor.room

import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import com.alekhin.fovmeapex.editor.room.database.ConfigFileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetConfigFiles(private val configFileRepository: ConfigFileRepository) {
    operator fun invoke(configFileOrder: ConfigFileOrder = ConfigFileOrder.Date(OrderType.Descending)): Flow<List<ConfigFile>> {
        return configFileRepository.getConfigFiles().map { configFiles ->
            when (configFileOrder.orderType) {
                OrderType.Ascending -> configFiles.sortedBy { it.timestamp }
                OrderType.Descending -> configFiles.sortedByDescending { it.timestamp }
            }
        }
    }
}