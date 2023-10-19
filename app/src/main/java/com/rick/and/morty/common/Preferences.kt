package com.rick.and.morty.common

import android.content.Context
import android.content.SharedPreferences

class Preferences (context : Context) {

    companion object {
        private const val APP_PREF_USER = "user_key"
        private const val KEY_SHOW_TUTORIAL = "show_tutorial"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences("Shared_prefs", Context.MODE_PRIVATE)

}

