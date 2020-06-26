package com.mahmoudelsadany.swvltask.customAdapters.movieDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmoudelsadany.swvltask.R
import com.mahmoudelsadany.swvltask.model.moviesImages.Photo
import kotlinx.android.synthetic.main.item_movie_image.view.*

class moviePhotosAdapter (private var movieImages: List<Photo>, var context : Context) :
    RecyclerView.Adapter<moviePhotosAdapter.movieImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): movieImagesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_image, parent, false)
        return movieImagesViewHolder(view)
    }

    override fun onBindViewHolder(vh: movieImagesViewHolder, position: Int) {
        //render
        vh.bind(movieImages[position])
    }

    override fun getItemCount(): Int {
        return movieImages.size
    }

    fun update(data: List<Photo>) {
        movieImages = data
        notifyDataSetChanged()
    }




    class movieImagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movie_image_item: ImageView = view.movie_image_item

        fun bind(_image: Photo) {

            var imageLink = "http://farm"+_image.farm+".static.flickr.com/"+_image.server+"/"+_image.id+"_"+_image.secret+".jpg"

            Glide.with(movie_image_item.context).load(imageLink).into(movie_image_item)
        }

    }



}