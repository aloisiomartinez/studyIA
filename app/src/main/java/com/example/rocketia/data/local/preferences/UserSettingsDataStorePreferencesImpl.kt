package com.example.rocketia.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val USER_SETTINGS_DATASTORE_NAME = "user_settings"
private const val SELECTED_STACK_KEY = "selected_stack"
private val SELECTED_STACK_PREFERENCES_KEY = stringPreferencesKey(name = SELECTED_STACK_KEY)
private const val FIRST_LAUNCH_KEY = "first_launch"
private val FIRST_LAUNCH_PREFERENCES_KEY = booleanPreferencesKey(name = FIRST_LAUNCH_KEY)

class UserSettingsDataStorePreferencesImpl(
    @ApplicationContext private val context: Context,
): UserSettingsPreferences {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS_DATASTORE_NAME)

    override val selectedStack: Flow<String?>
        get() = context.dataStore.data.map {
            preferences ->
                preferences[SELECTED_STACK_PREFERENCES_KEY]
        }

    override suspend fun changeSelectedStack(stack: String) {
        context.dataStore.edit { settings ->
            settings[SELECTED_STACK_PREFERENCES_KEY] = stack
        }
    }
}