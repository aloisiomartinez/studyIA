package com.example.rocketia.domain.usecase

import com.example.rocketia.domain.repository.AIChatRepository
import javax.inject.Inject


class SendUserQuestionUseCase(
    private val repository: AIChatRepository
) {

    suspend operator fun invoke(question: String, stack: String) {
        repository.sendUserQuestion(
            question = question
        )
    }

}