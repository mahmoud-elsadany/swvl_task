package com.mahmoudelsadany.swvltask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmoudelsadany.swvltask.repositories.moviesImagesRepository.moviesPhotosDataSource

class ViewModelFactory (private val repository: moviesPhotosDataSource): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return detailsViewModel(repository) as T
    }
}