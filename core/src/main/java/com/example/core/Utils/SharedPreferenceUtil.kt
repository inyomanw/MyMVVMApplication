package com.example.core.Utils

import android.content.SharedPreferences

class SharedPreferenceUtil(val sharedPreference: SharedPreferences) {

    object NetworkKeys {
        const val NETWORK_HEADER_TOKEN = "header_token"
        const val FCM_TOKEN = "fcm_token"
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreference.edit().putBoolean(key, value).apply()
    }

    fun setString(key: String, value: String) {
        sharedPreference.edit().putString(key, value).apply()
    }

    fun setInt(key: String, value: Int) {
        sharedPreference.edit().putInt(key, value).apply()
    }

    fun setLong(key: String, value: Long) {
        sharedPreference.edit().putLong(key, value).apply()
    }

    fun getBoolean(key: String) = sharedPreference.getBoolean(key, false)

    fun getString(key: String) = sharedPreference.getString(key, "")

    fun getInt(key: String) = sharedPreference.getInt(key, 0)

    fun getLong(key: String) = sharedPreference.getLong(key, 0)

    fun clear() {
        sharedPreference.edit().clear().apply()
    }
}