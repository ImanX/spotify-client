package com.github.imanx.spotify.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.imanx.spotify.END_POINT
import com.github.imanx.spotify.repository.SearchRepository
import com.github.imanx.spotify.utils.AuthenticationPreferences
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val mutableExceptionLiveData = MutableLiveData<Exception>()

    fun query(artistName: String) {


        val url = "$END_POINT/search?q=$artistName&type=artist&market=US&limit=20&offset=0"
        val builder =
            Request.Builder()
                .addHeader("Authorization", "Bearer " + AuthenticationPreferences(getApplication()).getToken()!!)
                .url(url).get().build();
        val call = OkHttpClient().newCall(builder);


        SearchRepository(call).fetch()

    }

}