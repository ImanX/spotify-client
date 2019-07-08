package com.github.imanx.spotify.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.imanx.spotify.R
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val scopes = arrayOf("app-remote-control");
        val spotifyRequest = AuthenticationRequest.Builder(
            "ba05b9cd59634cefa8493ac961d76ed6"
            , AuthenticationResponse.Type.TOKEN
            , "https://mydigipay.com/"
        )
            .setScopes(scopes)
            .build()


        AuthenticationClient.openLoginActivity(this, 100, spotifyRequest);
    }
}
