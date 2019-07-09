package com.github.imanx.spotify.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
abstract class Repository<T : Any>(private val call: Call) : Deserialize<T> {

    abstract fun onSuccess(response: T);
    abstract fun onFailure(code: Int, body: String)
    abstract fun onConnectionFailure();


    open fun fetch(): MutableLiveData<T> {
        val liveData = MutableLiveData<T>();
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (e is SocketTimeoutException || e is UnknownHostException) {
                    onConnectionFailure()
                    return
                }

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



