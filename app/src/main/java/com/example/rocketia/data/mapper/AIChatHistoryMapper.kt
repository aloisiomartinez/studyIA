package com.example.rocketia.data.mapper

import com.example.rocketia.data.local.database.AIChatTextEntity
import com.example.rocketia.domain.model.AIChatText
import com.example.rocketia.domain.model.AIChatTextType

fun AIChatTextEntity.toDomain() : AIChatText =
    when(this.from) {
        AIChatTextType.USER_QUESTION.name -> AIChatText.UserQuestion(question = this.text)
        AIChatTextType.AI_ANSWER.name -> AIChatText.AiAnswer(answer = this.text)
        else -> throw IllegalArgumentException("Invalid from value: ${this.from}")
    }

fun List<AIChatTextEntity>.toDomain(): List<AIChatText> =
    this.map { entity -> entity.toDomain() }