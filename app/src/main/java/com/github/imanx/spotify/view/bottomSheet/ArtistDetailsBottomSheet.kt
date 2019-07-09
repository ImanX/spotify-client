package com.github.imanx.spotify.view.bottomSheet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.github.imanx.spotify.R
import com.github.imanx.spotify.load
import com.github.imanx.spotify.models.Artist
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_details_artist.*

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class ArtistDetailsBottomSheet private constructor() : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(artist: Artist): ArtistDetailsBottomSheet {
            val bottomSheet = ArtistDetailsBottomSheet()
            val args = Bundle()
            args.putSerializable("ARTIST_KEY", artist)
            bottomSheet.arguments = args
            return bottomSheet
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.activity_details_artist, null);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val artist = arguments?.get("ARTIST_KEY") as Artist
        txt_followers.text = "${artist.followers.count} followers"
        txt_genres.text = artist.toStringGeners()
        txt_popularity.text = "${artist.popularity} popularity"
        txt_url.text = artist.externalURL.url

        txt_url.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(artist.externalURL.url)))
        }

        if (artist.images.isNullOrEmpty()) {
            img_view.setImageResource(android.R.drawable.ic_menu_report_image)
        } else {
            img_view.load(artist.images.first().url, 200, 200)
        }

    }

    fun show(manager: FragmentManager) {
        super.show(manager, this.javaClass.simpleName);
    }
}