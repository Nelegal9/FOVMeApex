package com.alekhin.fovmeapex.editor.room

import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import com.alekhin.fovmeapex.editor.room.database.ConfigFileRepository

class DeleteConfigFile(private val configFileRepository: ConfigFileRepository) {
    suspend operator fun invoke(configFile: ConfigFile) {
        configFileRepository.deleteConfigFile(configFile)
    }
}