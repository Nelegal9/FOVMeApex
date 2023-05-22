package com.alekhin.fovmeapex.editor.room.edit

data class ConfigFileTextFieldState(
    var text: String = "",
    val hint: String = "",
    val visible: Boolean = true
)