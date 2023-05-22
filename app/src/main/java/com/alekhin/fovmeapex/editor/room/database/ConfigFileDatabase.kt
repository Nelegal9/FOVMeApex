package com.alekhin.fovmeapex.editor.room.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ConfigFile::class], version = 1)
abstract class ConfigFileDatabase : RoomDatabase() {
    abstract val configFileDao: ConfigFileDao

    companion object {
        const val DATABASE_NAME="config_files_db"
    }
}