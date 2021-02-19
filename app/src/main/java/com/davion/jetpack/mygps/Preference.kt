package com.davion.jetpack.mygps

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object Preference {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context?) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setString(key: PreferenceKey, value: String?) {
        val editor = preferences.edit()
        editor.putString(key.string, value)
        editor.apply()
    }

    fun getString(key: PreferenceKey, defaultValue: String): String? {
        return preferences.getString(key.string, defaultValue)
    }

    fun setBoolean(key: PreferenceKey, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key.string, value)
        editor.apply()
    }

    fun getBoolean(key: PreferenceKey): Boolean {
        return preferences.getBoolean(key.string, false)
    }

    fun clear(key: PreferenceKey) {
        val editor = preferences.edit()
        editor.remove(key.string)
        editor.apply()
    }
}