package com.example.rocketia.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AIChatTextEntity::class], version = 1)
abstract class RocketIADatabase: RoomDatabase() {
    abstract fun aiChatHistoryDao(): AIChatHistoryDao
}
