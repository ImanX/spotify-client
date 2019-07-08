package com.github.imanx.spotify

import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */


fun getCallbackURL(): String  = "${Resources.getSystem().getString(R.string.scheme)}//${Resources.getSystem().getString(R.string.com_spotify_sdk_redirect_host)}"

