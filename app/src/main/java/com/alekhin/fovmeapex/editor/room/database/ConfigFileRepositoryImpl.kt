package com.alekhin.fovmeapex.editor.room.database

import kotlinx.coroutines.flow.Flow

class ConfigFileRepositoryImpl(private val configFileDao: ConfigFileDao) : ConfigFileRepository {
    override fun getConfigFiles(): Flow<List<ConfigFile>> {
        return configFileDao.getConfigFiles()
    }

    override suspend fun getConfigFile(id: Int): ConfigFile? {
        return configFileDao.getConfigFile(id)
    }

    override suspend fun insertConfigFile(configFile: ConfigFile) {
        configFileDao.insertConfigFile(configFile)
    }

    override suspend fun deleteConfigFile(configFile: ConfigFile) {
        configFileDao.deleteConfigFile(configFile)
    }
}