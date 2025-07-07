package com.example.rocketia.data.datasource

import com.example.rocketia.data.remote.api.AIAPIService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AiChatRemoteDataSourceImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val aiApiService: AIAPIService
): AIChatRemoteDataSource {
    override suspend fun sendPrompt(stack: String, question: String): String? = withContext(ioDispatcher) {
        aiApiService.sendPrompt(stack, question)
    }
}