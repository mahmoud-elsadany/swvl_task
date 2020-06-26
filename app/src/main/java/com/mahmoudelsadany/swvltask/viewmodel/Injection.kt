package com.mahmoudelsadany.swvltask.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.mahmoudelsadany.swvltask.repositories.moviesImagesRepository.moviesPhotosDataSource
import com.mahmoudelsadany.swvltask.repositories.moviesImagesRepository.moviesPhotosRepository

object Injection {
    private val moviesPhotosDataSource: moviesPhotosDataSource = moviesPhotosRepository()
    private val museumViewModelFactory = ViewModelFactory(moviesPhotosDataSource)

    fun providerRepository():moviesPhotosDataSource{
        return moviesPhotosDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return museumViewModelFactory
    }
}