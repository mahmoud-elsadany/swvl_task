package com.mahmoudelsadany.swvltask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudelsadany.swvltask.Data.OperationCallback
import com.mahmoudelsadany.swvltask.model.moviesImages.Photo
import com.mahmoudelsadany.swvltask.model.moviesImages.movieImages
import com.mahmoudelsadany.swvltask.repositories.moviesImagesRepository.moviesPhotosDataSource

class detailsViewModel (private val repository: moviesPhotosDataSource): ViewModel() {

    private val _moviesImages = MutableLiveData<List<Photo>>()
    val moviesImages: LiveData<List<Photo>> = _moviesImages

//    private val _isViewLoading=MutableLiveData<Boolean>()
//    val isViewLoading:LiveData<Boolean> = _isViewLoading
//
//    private val _onMessageError=MutableLiveData<Any>()
//    val onMessageError:LiveData<Any> = _onMessageError
//
//    private val _isEmptyList=MutableLiveData<Boolean>()
//    val isEmptyList:LiveData<Boolean> = _isEmptyList



    fun loadMoviesImages(movieTitle:String){
//        _isViewLoading.postValue(true)
        repository.retrieveMoviesPhotos(movieTitle,object: OperationCallback<movieImages> {
            override fun onError(error: String?) {
//                _isViewLoading.postValue(false)
//                _onMessageError.postValue( error)
            }

            override fun onSuccess(data: movieImages?) {
//                _isViewLoading.postValue(false)

                if(data?.photos?.photo == null){
//                    _isEmptyList.postValue(true)
                }else{
                    _moviesImages.value= data.photos.photo
                }
            }

        })
    }

}