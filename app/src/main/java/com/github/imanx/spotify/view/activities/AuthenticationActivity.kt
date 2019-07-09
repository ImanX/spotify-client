package com.github.imanx.spotify.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.imanx.spotify.R
import com.github.imanx.spotify.makeToast
import com.github.imanx.spotify.startActivity
import com.github.imanx.spotify.utils.AuthenticationPreferences
import com.github.imanx.spotify.utils.SpotifyAuthenticationHelper
import com.spotify.sdk.android.authentication.AuthenticationClient

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SpotifyAuthenticationHelper().startAuthentication(this);

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val response = AuthenticationClient.getResponse(resultCode, data)

        if (response.error != null || response.accessToken == null) {
            makeToast("${getString(R.string.err_authentication)} ${response.error}")
            return
        }


        AuthenticationPreferences(this).putToken(response.accessToken);
        startActivity(SearchActivity::class.java);


    }
}
