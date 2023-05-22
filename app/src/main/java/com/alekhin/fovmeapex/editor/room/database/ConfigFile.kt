package com.alekhin.fovmeapex.editor.room.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConfigFile(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val content: String,
    val timestamp: Long
)