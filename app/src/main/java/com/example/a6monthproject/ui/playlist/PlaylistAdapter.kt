package com.example.a6monthproject.ui.playlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a6monthproject.R
import com.example.a6monthproject.databinding.PlaylistItemBinding
import com.example.a6monthproject.model.Item
import com.example.a6monthproject.util.loadImage
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter

class PlaylistAdapter(val myContext: Context, private val onClick: (Item) -> Unit) : RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>() {

    private var list = arrayListOf<Item>()

    fun addPlayListItems(items: List<Item>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val binding =
            PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }
    }


    override fun getItemCount(): Int = list.size

    inner class PlaylistHolder(private var binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playList: Item) {
            binding.titleItem.text = playList.snippet?.title.toString()
            binding.descriptionItem.text = myContext.getString(
                R.string.series_count,
                playList.contentDetails?.itemCount.toString()
            )
            binding.imgItem.loadImage(playList.snippet?.thumbnails?.medium?.url)
        }
    }

    companion object {

        val PLDiffrentCallBack = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }


        }
    }

}