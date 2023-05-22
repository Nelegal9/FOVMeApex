package com.alekhin.fovmeapex.editor.room

sealed class ConfigFileOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): ConfigFileOrder(orderType)

    fun copy(orderType: OrderType): ConfigFileOrder {
        return when (this) {
            is Date -> Date(orderType)
        }
    }
}