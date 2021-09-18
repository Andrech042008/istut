package com.example.youtubeapi.ui.player

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.youtubeapi.ui.detail.DetailActivity.Companion.DESCRIPTION
import com.example.youtubeapi.ui.detail.DetailActivity.Companion.TITLE
import com.example.youtubeapi.ui.detail.DetailActivity.Companion.VIDEO_ID
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

@SuppressLint("StaticFieldLeak")
class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {

    private var viewModel: PlayerViewModel? = null

    private var mPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var videoId:String? = null

    override fun setupUI() {

        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        binding.backContainer.setOnClickListener {
            finish()
        }
        initPlayer()
        val title = intent.getStringExtra(TITLE)
        val desc = intent.getStringExtra(DESCRIPTION)
        binding.tvTitle.text = title
        binding.tvDesc.text = desc
    }

    override fun setupLiveData() {

    }

    override fun showDisconnectState() {

    }

    override fun inflateBinding(inflater: LayoutInflater): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    private fun initPlayer() {

        mPlayer = SimpleExoPlayer.Builder(this).build()
        binding.exoPlayer.player = mPlayer
        videoId = intent.getStringExtra(VIDEO_ID)
        val youtubeLink = "http://youtube.com/watch?v=$videoId"

        object : YouTubeExtractor(this) {

            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {

                if (ytFiles != null) {
                    val itag = 137
                    val audioTag = 140
                    val videoUrl = ytFiles[itag].url
                    val audioUrl = ytFiles[audioTag].url
                    val audioSourse: MediaSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(
                                MediaItem.fromUri(audioUrl)
                            )
                    val videoSource: MediaSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(
                                MediaItem.fromUri(videoUrl)
                            )
                    mPlayer?.setMediaSource(
                        MergingMediaSource(true, videoSource, audioSourse),
                        true
                    )
                    mPlayer?.prepare()
                    mPlayer?.playWhenReady = playWhenReady
                    mPlayer?.seekTo(currentWindow, playbackPosition)
                }
            }
        }.extract(youtubeLink)
    }

    override fun onStart() {
        super.onStart()
            initPlayer()

    }

    override fun onResume() {
        super.onResume()
            initPlayer()
            hideSystemUi()

    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
    }

    override fun onPause() {
        super.onPause()
            releasePlayer()

    }

    override fun onStop() {
            releasePlayer()
            super.onStop()

    }

    private fun releasePlayer() {
        if (mPlayer == null) {
            playWhenReady = mPlayer!!.playWhenReady
            playbackPosition = mPlayer!!.currentPosition
            currentWindow = mPlayer!!.currentWindowIndex
            mPlayer!!.release()
            mPlayer = null
        }
    }
}