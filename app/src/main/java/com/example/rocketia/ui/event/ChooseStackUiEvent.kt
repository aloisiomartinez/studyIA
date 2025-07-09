package com.example.rocketia.ui.event

sealed interface ChooseStackUiEvent {
    data class SelecteStack(val selectedStackName: String, val selectedStackChipId: Int): ChooseStackUiEvent
}