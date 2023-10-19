package com.rick.and.morty.ui.base

sealed class BaseEvent {
    data class ShowMessage(val message: Int): BaseEvent()
}