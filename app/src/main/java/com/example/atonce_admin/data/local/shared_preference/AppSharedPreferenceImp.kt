package com.example.atonce_admin.data.local.shared_preference

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreferenceImp (context: Context):AppSharedPreference {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var INSTANCE: AppSharedPreference? = null
        fun initSharedPreferences(context: Context) {
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppSharedPreferenceImp(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }

        fun getInstance() = INSTANCE!!
    }

   override fun <T> saveData(key: String, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
            apply()
        }
    }

    override fun <T> fetchData(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

}
