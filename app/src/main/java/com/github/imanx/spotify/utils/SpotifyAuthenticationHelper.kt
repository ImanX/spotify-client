package com.github.imanx.spotify.utils

import android.app.Activity
import com.github.imanx.spotify.BuildConfig
import com.github.imanx.spotify.R
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SpotifyAuthenticationHelper(private val hostActivity: Activity) {

    private val requestCode = 100;
    private val callbackURL = "${hostActivity.getString(R.string.scheme)}://${hostActivity.getString(R.string.host)}"
    private val scopes = arrayOf("app-remote-control");

    fun startAuthentication() {

        val spotifyRequest = AuthenticationRequest.Builder(
            BuildConfig.CLIENT_ID
            , AuthenticationResponse.Type.TOKEN
            , callbackURL
        )
            .setScopes(scopes)
            .build()


        AuthenticationClient.openLoginActivity(hostActivity, requestCode, spotifyRequest);
    }

}