package com.example.youtubeapi

import android.app.Application
import com.example.youtubeapi.ui.repository.Repository

class App: Application() {

    companion object {
        val repository = Repository()
    }
}