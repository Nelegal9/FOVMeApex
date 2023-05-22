package com.alekhin.fovmeapex.editor.room

import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import com.alekhin.fovmeapex.editor.room.database.ConfigFileRepository

class GetConfigFile(private val configFileRepository: ConfigFileRepository) {
    suspend operator fun invoke(id: Int): ConfigFile? {
        return configFileRepository.getConfigFile(id)
    }
}