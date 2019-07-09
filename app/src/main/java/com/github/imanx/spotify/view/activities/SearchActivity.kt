package com.github.imanx.spotify.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.github.imanx.spotify.viewModel.SearchViewModel

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a = ViewModelProviders.of(this).get(SearchViewModel::class.java);
        a.query("Iman");
    }
}