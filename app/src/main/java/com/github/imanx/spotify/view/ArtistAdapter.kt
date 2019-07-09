package com.github.imanx.spotify.view

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.imanx.spotify.R
import com.github.imanx.spotify.databinding.ItemArtistBinding
import com.github.imanx.spotify.load
import com.github.imanx.spotify.models.Artist

/**
 * Created by ImanX.
 * spotifychallenge | Copyrights 2019 ZarinPal Crop.
 */
class ArtistAdapter(private val items: List<Artist>) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    var onClickRecyclerViewItemListener: OnClickRecyclerViewItemListener<Artist>? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(View.inflate(parent.context, R.layout.item_artist, null));
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            onClickRecyclerViewItemListener?.onClick(it, item)
        }

        holder.binding.txtName.text = item.name;
        holder.binding.txtFollowers.text = "${item.followers.count} followers";
        if (item.images.isNullOrEmpty()) {
            holder.binding.imgView.setImageResource(android.R.drawable.ic_menu_report_image)
        } else {
            holder.binding.imgView.load(item.images.first().url)

        }


    }


    class ArtistViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val binding by lazy {
            DataBindingUtil.bind<ItemArtistBinding>(v)!!
        }

    }
}