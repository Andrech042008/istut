package com.example.youtubeapi.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ListDetailBinding
import com.example.youtubeapi.extensions.load
import com.example.youtubeapi.model.PlayListItem

class DetailAdapter(private val clickListener:(item:PlayListItem.Item)->Unit)
    : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private lateinit var binding: ListDetailBinding
    private val playList: ArrayList<PlayListItem.Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList[position])
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    fun addPlayList(list: ArrayList<PlayListItem.Item>?) {
        if (list != null) {
            playList.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding: ListDetailBinding = ListDetailBinding.bind(itemView)

        fun onBind(playList: PlayListItem.Item) {

            itemView.setOnClickListener{
                clickListener(playList)
            }
            binding.tvHeader.text = playList.snippet.title
            binding.imageView.load(playList.snippet.thumbnails.high.url)
            binding.tvPublished.text = playList.contentDetails.videoPublishedAt
        }
    }
}