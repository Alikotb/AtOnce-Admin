package com.example.atonce_admin.data.local.shared_preference

interface AppSharedPreference {
    fun <T> saveData(key: String, value: T)
    fun <T> fetchData(key: String, defaultValue: T): T
}