package com.example.rocketia.data.datasource

import com.example.rocketia.data.api.AIAPIService

class AiChatRemoteDataSourceImpl(
    private val aiApiService: AIAPIService
): AIChatRemoteDataSource {
    override suspend fun sendPrompt(stack: String, question: String): String? =
        aiApiService.sendPrompt(stack, question)
}