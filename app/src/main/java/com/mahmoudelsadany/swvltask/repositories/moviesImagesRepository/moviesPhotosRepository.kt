package com.mahmoudelsadany.swvltask.repositories.moviesImagesRepository

import android.util.Log
import com.mahmoudelsadany.swvltask.Data.ApiClient
import com.mahmoudelsadany.swvltask.Data.OperationCallback
import com.mahmoudelsadany.swvltask.Utils.myUtils
import com.mahmoudelsadany.swvltask.model.moviesImages.movieImages
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class moviesPhotosRepository() :moviesPhotosDataSource {

    private var call: Call<movieImages>?=null

    override fun retrieveMoviesPhotos(
        movieTitle: String,
        callback: OperationCallback<movieImages>) {
        call= ApiClient.build()?.getMovieImages(myUtils.method,
            myUtils.api_key,
            myUtils.format,
            myUtils.nojsoncallback,
            movieTitle,
            myUtils.page,
            myUtils.per_page)
        call?.enqueue(object : Callback<movieImages> {
            override fun onFailure(call: Call<movieImages>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<movieImages>, response: Response<movieImages>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        Log.v("hello", "data ${it}")
                        callback.onSuccess(it)
                    }else{
                        callback.onError("error")
                    }
                }
            }
        })
    }


    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}