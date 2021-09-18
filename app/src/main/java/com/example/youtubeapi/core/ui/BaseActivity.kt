package com.example.youtubeapi.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(){

    protected lateinit var binding: VB

    protected abstract fun inflateBinding(inflater:LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflateBinding(LayoutInflater.from(this))
        setContentView(binding.root)

        setupUI()
        setupLiveData()
        showDisconnectState()

    }

    abstract fun setupUI() // внутри этого метода мы иниц все view

    abstract fun setupLiveData() // иниц все LiveData

    abstract fun showDisconnectState() // Проверка интернета и вернуть Интернет активити

}
