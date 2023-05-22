package com.alekhin.fovmeapex.editor.room.edit

import androidx.compose.ui.focus.FocusState

sealed class AddEditConfigFileEvent {
    data class EnteredName(val value: String): AddEditConfigFileEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditConfigFileEvent()
    data class EnteredContent(val value: String): AddEditConfigFileEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditConfigFileEvent()
    object SaveConfigFile: AddEditConfigFileEvent()
}