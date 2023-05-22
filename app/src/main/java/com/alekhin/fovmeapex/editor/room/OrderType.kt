package com.alekhin.fovmeapex.editor.room

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}