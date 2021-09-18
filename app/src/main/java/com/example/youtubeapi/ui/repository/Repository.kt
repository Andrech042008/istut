package com.example.youtubeapi.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.utils.Constants
import com.example.youtubeapi.core.network.Resource
import com.example.youtubeapi.model.PlayListItem
import com.example.youtubeapi.model.PlayListJs
import com.example.youtubeapi.network.RetrofitClient
import com.example.youtubeapi.network.YoutubeApi
import kotlinx.coroutines.Dispatchers

class Repository {

    private var youtubeApi: YoutubeApi = RetrofitClient.create()

    fun fetchYoutubeApiPlayList(): LiveData<Resource<PlayListJs>> = liveData(Dispatchers.IO) {

        emit(Resource.loading(null))

        val response = youtubeApi.fetchAllPlayLists(Constants.PART, Constants.CHANNEL_ID, API_KEY)

        emit(
            if (response.isSuccessful)
                Resource.success(response.body(), response.code())
            else
                Resource.error(response.message(), response.body(), response.code())
        )
    }

    fun fetchYoutubeApiPlayListItems(playListId:String):LiveData<Resource<PlayListItem>> = liveData(Dispatchers.IO){

        emit(Resource.loading(null))

        val response = youtubeApi.fetchPlayListItems(Constants.PART,playListId,API_KEY)

        emit(
            if (response.isSuccessful)
                Resource.success(response.body(), response.code())
            else
                Resource.error(response.message(), response.body(), response.code())
        )
    }
}