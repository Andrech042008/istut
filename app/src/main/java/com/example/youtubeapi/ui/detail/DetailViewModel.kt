package com.example.youtubeapi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.Resource
import com.example.youtubeapi.model.PlayListItem

class DetailViewModel : ViewModel() {

    fun fetchPlayListItems(playListId:String):LiveData<Resource<PlayListItem>>{
        return App.repository.fetchYoutubeApiPlayListItems(playListId)
    }
}