package com.github.imanx.spotify.repository

import com.github.imanx.spotify.fromJson
import com.github.imanx.spotify.models.Artist
import com.google.gson.Gson
import okhttp3.Call
import org.json.JSONObject

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SearchRepository(call: Call) : Repository<List<Artist>>(call) {
    override fun onDeserialize(body: String, gson: Gson): List<Artist> {
        val b = JSONObject(body)
            .getJSONObject("artists")
            .getJSONArray("items")
            .toString()
        return gson.fromJson(b)
    }
}