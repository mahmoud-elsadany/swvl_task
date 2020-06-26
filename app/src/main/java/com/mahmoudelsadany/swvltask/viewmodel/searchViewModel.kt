package com.mahmoudelsadany.swvltask.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudelsadany.swvltask.model.localMovies.movie
import com.mahmoudelsadany.swvltask.repositories.moviesRepository.getLocalMovies

class searchViewModel : ViewModel() {


    private val _movies = MutableLiveData<List<movie>>().apply { value = emptyList() }
    val movies: LiveData<List<movie>> = _movies


    fun loadMovies(context: Context) {
        _movies.value = getLocalMovies.getMovies(context)
    }






}