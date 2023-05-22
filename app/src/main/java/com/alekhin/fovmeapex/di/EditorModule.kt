package com.alekhin.fovmeapex.di

import android.app.Application
import androidx.room.Room
import com.alekhin.fovmeapex.editor.room.AddConfigFile
import com.alekhin.fovmeapex.editor.room.ConfigFileUseCase
import com.alekhin.fovmeapex.editor.room.DeleteConfigFile
import com.alekhin.fovmeapex.editor.room.GetConfigFile
import com.alekhin.fovmeapex.editor.room.GetConfigFiles
import com.alekhin.fovmeapex.editor.room.database.ConfigFileDatabase
import com.alekhin.fovmeapex.editor.room.database.ConfigFileRepository
import com.alekhin.fovmeapex.editor.room.database.ConfigFileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EditorModule {

    @Provides
    @Singleton
    fun provideConfigFileDatabase(app: Application): ConfigFileDatabase {
        return Room.databaseBuilder(app, ConfigFileDatabase::class.java, ConfigFileDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideConfigFileRepository(db: ConfigFileDatabase): ConfigFileRepository {
        return ConfigFileRepositoryImpl(db.configFileDao)
    }

    @Provides
    @Singleton
    fun provideConfigFileUseCase(configFileRepository: ConfigFileRepository): ConfigFileUseCase {
        return ConfigFileUseCase(
            getConfigFiles = GetConfigFiles(configFileRepository),
            deleteConfigFile = DeleteConfigFile(configFileRepository),
            addConfigFile = AddConfigFile(configFileRepository),
            getConfigFile = GetConfigFile(configFileRepository)
        )
    }
}