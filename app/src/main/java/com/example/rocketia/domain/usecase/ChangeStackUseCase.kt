package com.example.rocketia.domain.usecase

import com.example.rocketia.domain.model.AIChatTextType
import com.example.rocketia.domain.repository.AIChatRepository
import javax.inject.Inject

class ChangeStackUseCase (
    private val repository: AIChatRepository
) {

    suspend operator fun invoke(stack: String) {
        repository.changeStack(stack = stack)
    }

}