package com.github.imanx.spotify.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
abstract class Repository<T : Any>(private val call: Call) : Deserialize<T> {
    open fun fetch(): LiveData<T> {
        val liveData = MutableLiveData<T>();
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                liveData.postValue(null);
            }

            override fun onResponse(call: Call, response: Response) {
                response.takeIf { it.isSuccessful }?.apply {
                    val result = onDeserialize(body?.string()!!, GsonBuilder().create())
                    liveData.postValue(result);
                }
            }

        })

        return liveData;
    }



}



