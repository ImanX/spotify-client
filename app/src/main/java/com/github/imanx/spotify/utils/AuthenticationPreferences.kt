package com.github.imanx.spotify.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class AuthenticationPreferences(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE);
    private var editor: SharedPreferences.Editor = preferences.edit();

    fun putToken(token: String) {
        editor.putString("TOKEN", token).commit();
    }

    fun getToken(): String? = preferences.getString("TOKEN", null);

    fun hasToken(): Boolean = getToken() != null;
}