package com.capstone.artwhale.util

import android.content.Context
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    fun saveJwt(jwtToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(X_ACCESS_TOKEN, jwtToken)

        editor.apply()
    }

    fun getJwt(): String? = sharedPreferences.getString(X_ACCESS_TOKEN, null)

    companion object {
        const val X_ACCESS_TOKEN: String = "Authorization"
        const val TAG: String = "ART-WHALE-APP"
    }
}
