package com.github.imanx.spotify.viewModel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.imanx.spotify.utils.AuthenticationPreferences
import com.github.imanx.spotify.utils.SpotifyAuthenticationHelper
import com.spotify.sdk.android.authentication.AuthenticationClient

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {


    private val mutableExceptionLiveData = MutableLiveData<Exception>()
    private val mutableAuthenticationLiveData = MutableLiveData<Unit>()


    fun getExceptionLiveData() = mutableExceptionLiveData
    fun getAuthenticationLiveData() = mutableAuthenticationLiveData


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val response = AuthenticationClient.getResponse(resultCode, data)

        if (response.error != null || response.accessToken == null) {
            getExceptionLiveData().postValue(Exception(response.error))
            return
        }

        AuthenticationPreferences(getApplication()).putToken(response.accessToken)
        mutableAuthenticationLiveData.postValue(null);
    }


}