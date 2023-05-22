package com.alekhin.fovmeapex.editor.room

import com.alekhin.fovmeapex.editor.room.database.ConfigFile
import com.alekhin.fovmeapex.editor.room.database.ConfigFileRepository

class AddConfigFile(private val configFileRepository: ConfigFileRepository) {

    @Throws(InvalidConfigFileException::class)
    suspend operator fun invoke(configFile: ConfigFile) {
        if (configFile.name.isBlank()) {
            throw  InvalidConfigFileException("The name of the config file can't be empty.")

        }

        configFileRepository.insertConfigFile(configFile)
    }
}