package com.github.imanx.spotify.repository

import com.google.gson.Gson

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
interface Deserialize<T> {
    fun onDeserialize(body: String, gson: Gson): T
}