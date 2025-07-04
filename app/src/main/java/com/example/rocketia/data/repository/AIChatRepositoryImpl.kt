package com.example.rocketia.data.repository

import com.example.rocketia.data.datasource.AIChatLocalDataSource
import com.example.rocketia.data.datasource.AIChatRemoteDataSource
import com.example.rocketia.data.local.database.AIChatTextEntity
import com.example.rocketia.data.mapper.toDomain
import com.example.rocketia.domain.model.AIChatText
import com.example.rocketia.domain.model.AIChatTextType
import com.example.rocketia.domain.repository.AIChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AIChatRepositoryImpl(
    private val aiChatLocalDataSource: AIChatLocalDataSource,
    private val aiChatRemoteDataSource: AIChatRemoteDataSource
): AIChatRepository {

    override val selectedStack: Flow<String>
        get() = aiChatLocalDataSource.selectedStack

    override val firstLaunch: Flow<Boolean>
        get() = aiChatLocalDataSource.firstLaunch

    override val aiChatBySelectedStack: Flow<List<AIChatText>>
        get() = aiChatLocalDataSource.aiCurrentChatBySelectedStack.map { currentChatEntity ->
            currentChatEntity.toDomain()
        }

    override suspend fun sendUserQuestion(question: String, stack: String) {
        val answer = aiChatRemoteDataSource.sendPrompt(question = question, stack = stack)

        answer?.let {
            aiChatLocalDataSource.insertAIChatConversation(
                question = createUserQuestionEntity(question = question, stack = stack),
                answer = createAIAnswerEntity(answer = answer, stack = stack)
            )
        }
    }

    private fun createUserQuestionEntity(question: String, stack: String): AIChatTextEntity =
        AIChatTextEntity(
            stack = stack,
            text = question,
            from = AIChatTextType.USER_QUESTION.name,
            dateTime = System.currentTimeMillis()
        )

    private fun createAIAnswerEntity(answer: String, stack: String): AIChatTextEntity =
        AIChatTextEntity(
            stack = stack,
            text = answer,
            from = AIChatTextType.AI_ANSWER.name,
            dateTime = System.currentTimeMillis()
        )

    override suspend fun changeStack(stack: String) {
        aiChatLocalDataSource.changeSelectedStack(stack)
    }

    override suspend fun changeFirstLaunch() {
        aiChatLocalDataSource.changeFirstLaunch()
    }
}