package com.alekhin.fovmeapex.editor.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigFileDao {

    @Query("SELECT * FROM configFile")
    fun getConfigFiles(): Flow<List<ConfigFile>>

    @Query("SELECT * FROM configFile WHERE id = :id")
    suspend fun getConfigFile(id: Int): ConfigFile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfigFile(configFile: ConfigFile)

    @Delete
    suspend fun deleteConfigFile(configFile: ConfigFile)
}