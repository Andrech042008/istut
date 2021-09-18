package com.example.youtubeapi.ui.detail

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.core.network.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityDetailBinding
import com.example.youtubeapi.model.PlayListItem
import com.example.youtubeapi.ui.detail.adapter.DetailAdapter
import com.example.youtubeapi.ui.player.PlayerActivity
import com.example.youtubeapi.ui.playlist.PlayListActivity.Companion.PLAY_LIST_ID
import com.example.youtubeapi.ui.playlist.PlayListActivity.Companion.TITLE
import javax.security.auth.DestroyFailedException

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    private var viewModel: DetailViewModel? = null
    private val detailAdapter: DetailAdapter by lazy { DetailAdapter(this::clickListener) }
    private var playListId: String? = null

    override fun setupUI() {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.backContainer.setOnClickListener {
            finish()
        }
        binding.fab.setOnClickListener {
            Intent(this, PlayerActivity::class.java).apply {
                startActivity(this)
            }
        }
        playListId = intent.getStringExtra(PLAY_LIST_ID)
    }

    override fun setupLiveData() {

        viewModel?.fetchPlayListItems(playListId.toString())?.observe(this, { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    detailAdapter.addPlayList(response.data?.items)

                    (response.data?.items?.size.toString() + " video series"
                            ).also { binding.tvVideoSeriesPlay.text = it }
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, response.code.toString(), Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        })

        binding.recyclerviewPlay.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = detailAdapter
        }
        val string = intent.getStringExtra(TITLE)
        binding.tvHeaderPlay.text = string

    }

    override fun showDisconnectState() {

    }

    override fun inflateBinding(inflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private fun clickListener(items: PlayListItem.Item) {

        Intent(this,PlayerActivity::class.java).apply {
            putExtra(VIDEO_ID,items.snippet.resourceId.videoId)
            putExtra(TITLE,items.snippet.title)
            putExtra(DESCRIPTION,items.snippet.description)
            startActivity(this)
        }
        Log.d("tag", "videoId: $items.snippet.resourceId.videoId")

    }
    companion object{
        const val VIDEO_ID = "video"
        const val TITLE  = "title"
        const val DESCRIPTION = "description"
    }
}
