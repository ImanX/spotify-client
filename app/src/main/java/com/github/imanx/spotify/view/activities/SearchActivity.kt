package com.github.imanx.spotify.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.imanx.spotify.R
import com.github.imanx.spotify.databinding.ActivitySearchBinding
import com.github.imanx.spotify.makeToast
import com.github.imanx.spotify.models.Artist
import com.github.imanx.spotify.setContentViewByBinding
import com.github.imanx.spotify.view.ArtistAdapter
import com.github.imanx.spotify.view.OnClickRecyclerViewItemListener
import com.github.imanx.spotify.view.bottomSheet.ArtistDetailsBottomSheet
import com.github.imanx.spotify.viewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.Exception

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class SearchActivity : AppCompatActivity() {

    private val searchVieModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java);
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewByBinding<ActivitySearchBinding>(R.layout.activity_search);
        search_bar.setOnQueryTextListener(onQuerySearchView);

        searchVieModel.getExceptionLiveData().observe(this, exceptionObserver);

    }


    private val onQuerySearchView = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchVieModel.query(query)
            searchVieModel.getSearchArtistLiveData()?.observe(this@SearchActivity, searchObserver);
            return false;
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false;
        }

    }

    private val onClickRecyclerViewItemListener = object : OnClickRecyclerViewItemListener<Artist> {
        override fun onClick(v: View?, item: Artist) {
            ArtistDetailsBottomSheet.newInstance(item).show(supportFragmentManager);
        }
    }


    private val exceptionObserver = Observer<Exception> {
        if (it == null) {
            makeToast(getString(R.string.connection_failure))
            return@Observer
        }

        makeToast("${getString(R.string.error_message)} ${it.message}")


    }

    private val searchObserver = Observer<List<Artist>> {
        val adapter = ArtistAdapter(it);
        adapter.onClickRecyclerViewItemListener = onClickRecyclerViewItemListener;
        recycler_view.adapter = adapter;
        recycler_view.setHasFixedSize(true);
    }


}


