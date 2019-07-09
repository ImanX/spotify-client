package com.github.imanx.spotify.models

import com.google.gson.annotations.SerializedName

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
data class Artist(
    val followers: Follower,
    val genres: Array<String>,
    val images: Array<Image>,
    val name: String,
    val popularity: Int,
    val href: String
)

data class Follower(@SerializedName("total") val count: Int);

data class Image(val height: Int, val width: Int, val url: String)
