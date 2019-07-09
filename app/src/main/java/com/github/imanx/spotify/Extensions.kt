package com.github.imanx.spotify

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
fun Activity.getView(): View = this.window.decorView.rootView;

fun Activity.makeToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show();
}

fun Activity.startActivity(clazz: Class<*>) = startActivity(Intent(this, clazz));

fun ImageView.load(url: String, sizeWidth: Int = 100, sizeHeight: Int = 100) {
    Picasso.get()
        .load(Uri.parse(url))
        .resize(sizeWidth, sizeHeight)
        .placeholder(android.R.drawable.ic_menu_report_image)
        .into(this);
}

fun <T : ViewDataBinding> AppCompatActivity.setContentViewByBinding(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout);
}

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
