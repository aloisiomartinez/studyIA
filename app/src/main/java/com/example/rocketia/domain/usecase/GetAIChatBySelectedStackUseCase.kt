package com.example.rocketia.domain.usecase

import com.example.rocketia.domain.model.AIChatText
import com.example.rocketia.domain.repository.AIChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAIChatBySelectedStackUseCase(
    private val repository: AIChatRepository
) {

    operator fun invoke(): Flow<List<AIChatText>> {
        return repository.aiChatBySelectedStack
    }

}