package com.example.rocketia.domain.usecase

import com.example.rocketia.domain.repository.AIChatRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class CheckHasSelectedStackUseCase @Inject constructor(
    private val repository: AIChatRepository
) {
    suspend operator fun invoke(): Boolean =
        repository.selectedStack.firstOrNull()?.isNotEmpty() == true
}