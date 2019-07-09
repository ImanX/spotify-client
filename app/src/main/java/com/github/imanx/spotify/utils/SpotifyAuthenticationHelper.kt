package com.github.imanx.spotify.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.imanx.spotify.R
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
@SuppressLint("Registered")
class SpotifyAuthenticationHelper {

    private val requestCode = 100;

    fun startAuthentication(hostActivity: Activity) {
        val callbackURL = "${hostActivity.getString(R.string.scheme)}://${hostActivity.getString(R.string.host)}"
        val scopes = arrayOf("app-remote-control");
        val spotifyRequest = AuthenticationRequest.Builder(
            "ba05b9cd59634cefa8493ac961d76ed6"
            , AuthenticationResponse.Type.TOKEN
            , callbackURL
        )
            .setScopes(scopes)
            .build()


        AuthenticationClient.openLoginActivity(hostActivity, requestCode, spotifyRequest);
    }

}