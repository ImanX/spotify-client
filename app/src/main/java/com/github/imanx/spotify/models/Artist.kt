package com.github.imanx.spotify.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
data class Artist(
    val followers: Follower,
    val genres: Array<String>,
    val images: Array<Image>? = null,
    val name: String,
    val popularity: Int,
    val href: String,
    @SerializedName("external_urls") val externalURL: ExternalURL
) : Serializable {
    fun toStringGeners(): String {
        val stringBuilder = StringBuilder()
        genres.forEach {
            stringBuilder.append(it)
            stringBuilder.append(", ")
        }

        return stringBuilder.toString()
    }
}

data class Follower(@SerializedName("total") val count: Int) : Serializable

data class Image(val height: Int, val width: Int, val url: String) : Serializable

data class ExternalURL(@SerializedName("spotify") val url: String? = null) : Serializable
