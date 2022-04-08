package com.llyods.assignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.llyods.assignment.R
import com.llyods.assignment.data.response.Artist
import com.llyods.assignment.databinding.ItemArtistBinding
import com.llyods.assignment.loadImageOrDefault
import kotlin.properties.Delegates

/**
 * Naming convention for lambda, passed when a item is tapped
 */
typealias ItemClickListener<T> = (T) -> Unit

class TopArtistAdapter(
    private val listner: ItemClickListener<Artist>,
) : RecyclerView.Adapter<TopArtistAdapter.ArtistViewHolder>() {

    var itemList: List<Artist> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    class ArtistViewHolder(
        private val context: Context,
        private val binding: ItemArtistBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist) {
            val imageUrl = artist.image.filter {
                it.size == "large"
            }.single().text

            binding.imgArtist.loadImageOrDefault(imageUrl)
            binding.txtName.text = artist.name
            binding.txtPlaycount.text =
                artist.playcount?.let {
                    String.format(context.getString(R.string.txt_playcount),
                        it)
                }
            binding.txtListeners.text = artist.listeners?.let {
                String.format(context.getString(R.string.txt_listner), it)
            }
            binding.txtWebsite.text = artist.url?.let {
                String.format(context.getString(R.string.txt_website), it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder =
        ArtistViewHolder(parent.context,
            ItemArtistBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {

        val artist = itemList[position]
        holder.bind(artist)
        holder.itemView.setOnClickListener {
            listner(artist)
        }

    }

    override fun getItemCount() = itemList.size
}