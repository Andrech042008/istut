package com.example.youtubeapi.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ListItemBinding
import com.example.youtubeapi.extensions.load
import com.example.youtubeapi.model.PlayListJs

class PlayListAdapter(private val clickListener: (item: PlayListJs.Item) -> Unit) :
    RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding
    private val playList: ArrayList<PlayListJs.Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList[position])
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    fun addPlayList(list: ArrayList<PlayListJs.Item>?) {
        if (list != null) {
            playList.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var binding: ListItemBinding = ListItemBinding.bind(itemView)

        fun onBind(playList: PlayListJs.Item?) {

            itemView.setOnClickListener {
                if (playList != null) {
                    clickListener(playList)
                }
            }
            binding.tvHeader.text = playList?.snippet?.title
                binding.imageView.load(playList?.snippet?.thumbnails?.high?.url.toString ())
            (playList?.contentDetails?.itemCount.toString() + " Video series").also {
                binding.tvSeries.text = it
            }
        }
    }
}