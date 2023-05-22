package com.alekhin.fovmeapex.editor.room.database

import kotlinx.coroutines.flow.Flow

interface ConfigFileRepository {
    fun getConfigFiles(): Flow<List<ConfigFile>>
    suspend fun getConfigFile(id: Int): ConfigFile?
    suspend fun insertConfigFile(configFile: ConfigFile)
    suspend fun deleteConfigFile(configFile: ConfigFile)
}