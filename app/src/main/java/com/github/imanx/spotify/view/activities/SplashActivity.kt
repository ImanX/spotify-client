package com.github.imanx.spotify.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.imanx.spotify.getView
import com.github.imanx.spotify.startActivity
import com.github.imanx.spotify.utils.AuthenticationPreferences

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authenticationPreferences = AuthenticationPreferences(this)
        val activityClass = if (authenticationPreferences.hasToken()) {
            SearchActivity::class.java
        } else {
            AuthenticationActivity::class.java
        }



        getView().postDelayed({
            startActivity(activityClass);
            finish()
        }, (2 * 1000))


    }
}