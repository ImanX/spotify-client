package com.github.imanx.spotify.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.imanx.spotify.END_POINT
import com.github.imanx.spotify.fromJson
import com.github.imanx.spotify.models.Artist
import com.github.imanx.spotify.repository.Repository
import com.github.imanx.spotify.utils.AuthenticationPreferences
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val mutableExceptionLiveData = MutableLiveData<Exception>()
    private var mutableArtistsLiveData: MutableLiveData<List<Artist>>? = null


    fun getSearchArtistLiveData() = mutableArtistsLiveData;
    fun getExceptionLiveData() = mutableExceptionLiveData


    fun query(artistName: String?) {


        if (artistName == null || artistName.length < 3) {
            return
        }

        val url = "$END_POINT/search?q=$artistName&type=artist&market=US&limit=50&offset=0"
        val authorization = AuthenticationPreferences(getApplication()).getToken().let {
            "Bearer $it";
        }
        val builder = Request.Builder()
            .addHeader("Authorization", authorization)
            .url(url)
            .get()
            .build();

        val call = OkHttpClient().newCall(builder)

        mutableArtistsLiveData = object : Repository<List<Artist>>(call) {
            override fun onSuccess(response: List<Artist>) {
                mutableArtistsLiveData?.postValue(response)
            }

            override fun onFailure(code: Int, body: String) {
                mutableExceptionLiveData.postValue(Exception(code.toString()))
            }

            override fun onConnectionFailure() {
                mutableExceptionLiveData.postValue(null)
            }

            override fun onDeserialize(body: String, gson: Gson): List<Artist> {
                val json = JSONObject(body)
                    .getJSONObject("artists")
                    .getJSONArray("items")
                    .toString()

                return gson.fromJson(json)
            }

        }.fetch();

    }

}