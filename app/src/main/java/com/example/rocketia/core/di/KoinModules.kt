package com.example.rocketia.core.di

import androidx.room.Room
import com.example.rocketia.data.datasource.AIChatLocalDataSource
import com.example.rocketia.data.datasource.AIChatLocalDataSourceImpl
import com.example.rocketia.data.datasource.AIChatRemoteDataSource
import com.example.rocketia.data.datasource.AiChatRemoteDataSourceImpl
import com.example.rocketia.data.local.database.AIChatHistoryDao
import com.example.rocketia.data.local.database.ROCKET_AI_DATABASE_NAME
import com.example.rocketia.data.local.database.RocketIADatabase
import com.example.rocketia.data.local.preferences.UserSettingsDataStorePreferencesImpl
import com.example.rocketia.data.local.preferences.UserSettingsPreferences
import com.example.rocketia.data.remote.api.AIAPIService
import com.example.rocketia.data.remote.api.AIGeminiAPIServiceImpl
import com.example.rocketia.data.repository.AIChatRepositoryImpl
import com.example.rocketia.domain.repository.AIChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single<CoroutineDispatcher>(named("IO")) { Dispatchers.IO }

    single<AIAPIService> { AIGeminiAPIServiceImpl() }
    single<UserSettingsPreferences> { UserSettingsDataStorePreferencesImpl(context = androidApplication()) }
    single<RocketIADatabase> {
        Room.databaseBuilder(
            androidApplication(),
            RocketIADatabase::class.java,
            ROCKET_AI_DATABASE_NAME
        ).build()
    }
    single<AIChatHistoryDao> { get<RocketIADatabase>().aiChatHistoryDao() }

    single<AIChatLocalDataSource> { AIChatLocalDataSourceImpl(get(named("IO")), get(), get()) }
    single<AIChatRemoteDataSource> { AiChatRemoteDataSourceImpl(get(named("IO")), get()) }

    single<AIChatRepository> { AIChatRepositoryImpl(get(), get()) }
}

val domainModule = module {}

val uiModule = module {}