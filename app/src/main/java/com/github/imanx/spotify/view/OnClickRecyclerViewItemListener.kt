package com.github.imanx.spotify.view

import android.view.View

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
interface OnClickRecyclerViewItemListener<T> : View.OnClickListener {
    override fun onClick(v: View?) {
    }

    fun onClick(v: View?, item: T);
}