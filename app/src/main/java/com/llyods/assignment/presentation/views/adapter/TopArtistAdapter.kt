package com.llyods.assignment.presentation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.llyods.assignment.R
import com.llyods.assignment.databinding.ItemArtistBinding
import com.llyods.assignment.domain.model.TopArtist
import com.llyods.assignment.loadImageOrDefault
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Naming convention for lambda, passed when a item is tapped
 */
typealias ItemClickListener<T> = (T) -> Unit

class TopArtistAdapter @Inject constructor()
    : RecyclerView.Adapter<TopArtistAdapter.ArtistViewHolder>() {

    lateinit var listner: ItemClickListener<TopArtist>

//    fun setItemClickListner(listner: ItemClickListener<TopArtist>){
//        this.listner = listner
//    }

    var itemList: List<TopArtist> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    class ArtistViewHolder(
        private val context: Context,
        private val binding: ItemArtistBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(topArtist: TopArtist) {
            val imageUrl = topArtist.image.filter {
                it.size == "large"
            }.single().text

            binding.imgArtist.loadImageOrDefault(imageUrl)
            binding.txtName.text = topArtist.name
            binding.txtPlaycount.text =
                topArtist.playcount?.let {
                    String.format(context.getString(R.string.txt_playcount),
                        it)
                }
            binding.txtListeners.text = topArtist.listeners?.let {
                String.format(context.getString(R.string.txt_listner), it)
            }
            binding.txtWebsite.text = topArtist.url?.let {
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