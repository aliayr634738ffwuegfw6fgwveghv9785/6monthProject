package com.example.a6monthproject.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a6monthproject.databinding.PlaylistItemBinding
import com.example.a6monthproject.model.Item
import com.example.a6monthproject.util.loadImage

class PlayListDetailsAdapter(private val onClick: (Item) -> Unit) :
    RecyclerView.Adapter<PlayListDetailsAdapter.PlaylistDetailsHolder>() {

    private var list = arrayListOf<Item>()

    fun addPlayListItems(items: List<Item>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailsHolder {
        val binding =
            PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistDetailsHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistDetailsHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }

    }

    override fun getItemCount(): Int = list.size

    inner class PlaylistDetailsHolder(private var binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playList: Item) {
            binding.titleItem.text = playList.snippet?.title.toString()
            binding.descriptionItem.text = playList.snippet?.publishedAt.toString()
            binding.imgItem.loadImage(playList.snippet?.thumbnails?.medium?.url)
            binding.darkFon.visibility = View.GONE
            binding.playlistFon.visibility = View.GONE
        }
    }
}