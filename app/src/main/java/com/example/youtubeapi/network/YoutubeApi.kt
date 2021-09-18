package com.example.youtubeapi.network

import com.example.youtubeapi.model.PlayListJs
import com.example.youtubeapi.model.PlayListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("playlists")
     suspend fun fetchAllPlayLists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") result:Int = 20
    ): Response<PlayListJs?>

     @GET("playlistItems")
     suspend fun fetchPlayListItems(
         @Query("part") part: String,
         @Query("playlistId") playListId: String,
         @Query("key") apiKey: String,
         @Query("maxResults") result:Int = 50
     ):Response<PlayListItem?>
}