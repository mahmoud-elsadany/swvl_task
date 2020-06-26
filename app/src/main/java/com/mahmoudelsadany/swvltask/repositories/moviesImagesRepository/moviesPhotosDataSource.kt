package com.mahmoudelsadany.swvltask.repositories.moviesImagesRepository

import com.mahmoudelsadany.swvltask.Data.OperationCallback
import com.mahmoudelsadany.swvltask.model.moviesImages.movieImages

interface moviesPhotosDataSource {


    fun retrieveMoviesPhotos(movieTitle:String, callback: OperationCallback<movieImages>)
    fun cancel()

}