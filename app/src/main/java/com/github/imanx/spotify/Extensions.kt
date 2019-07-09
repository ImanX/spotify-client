package com.github.imanx.spotify

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
fun Activity.getView(): View = this.window.decorView.rootView;

fun Activity.makeToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show();
}

fun Activity.startActivity(clazz: Class<*>) = startActivity(Intent(this, clazz));