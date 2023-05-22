package com.alekhin.fovmeapex.editor.room

sealed class UIEvent {
    data class ShowSnackbar(val message: String): UIEvent()
    object SaveConfigFile: UIEvent()
}