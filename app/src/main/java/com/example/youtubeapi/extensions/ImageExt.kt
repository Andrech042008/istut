package com.example.youtubeapi.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.youtubeapi.R

fun ImageView.load(s: String) {
    val imageView = findViewById<ImageView>(R.id.imageView)
    Glide.with(context).load(s).centerCrop().into(imageView)
}
