package com.github.imanx.spotify

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
fun Activity.getView(): View = this.window.decorView.rootView;

fun Activity.makeToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show();
}

fun Activity.startActivity(clazz: Class<*>) = startActivity(Intent(this, clazz));

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
