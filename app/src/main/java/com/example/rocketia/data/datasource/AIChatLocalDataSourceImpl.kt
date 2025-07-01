package com.example.rocketia.data.datasource

import com.example.rocketia.data.local.database.AiChatTextEntity
import kotlinx.coroutines.flow.Flow

class AIChatLocalDataSourceImpl: AIChatLocalDataSource {
    override val aiCurrentChatBySelectedStack: Flow<List<AiChatTextEntity>>
        get() = TODO("Not yet implemented")

    override suspend fun insertAiChatConversation(
        question: AiChatTextEntity,
        answer: AiChatTextEntity
    ) {
        TODO("Not yet implemented")
    }

    override val selectedStack: Flow<String>
        get() = TODO("Not yet implemented")

    override suspend fun changeSelectedStack(stack: String) {
        TODO("Not yet implemented")
    }

    override val firstLaunch: Flow<Boolean>
        get() = TODO("Not yet implemented")

    override suspend fun changeFirstLaunch(firstLaunch: Boolean) {
        TODO("Not yet implemented")
    }
}