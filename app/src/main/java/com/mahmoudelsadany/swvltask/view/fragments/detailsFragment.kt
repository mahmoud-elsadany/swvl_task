package com.mahmoudelsadany.swvltask.view.fragments

import android.content.Context
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudelsadany.swvltask.R
import com.mahmoudelsadany.swvltask.customAdapters.movieDetails.moviePhotosAdapter
import com.mahmoudelsadany.swvltask.customAdapters.search.searchMoviesAdpater
import com.mahmoudelsadany.swvltask.model.localMovies.movie
import com.mahmoudelsadany.swvltask.model.moviesImages.Photo
import com.mahmoudelsadany.swvltask.viewmodel.Injection
import com.mahmoudelsadany.swvltask.viewmodel.detailsViewModel
import com.mahmoudelsadany.swvltask.viewmodel.searchViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_search.*

class detailsFragment : Fragment() {


    private lateinit var mViewModel: detailsViewModel
    private lateinit var currentMovie: movie

    private lateinit var adapter: moviePhotosAdapter
    private lateinit var mContext: Context



    companion object {

        fun newInstance(): detailsFragment {
            return detailsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.getSerializable("movie")?.let {
            currentMovie = it as movie
        }

        return inflater.inflate(R.layout.fragment_details, container, false)
    }







    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(detailsViewModel::class.java)
        mViewModel.moviesImages.observe(viewLifecycleOwner, renderMoviesImages)
    }

    //observers
    private val renderMoviesImages = Observer<List<Photo>> {
        Log.v("hello", "data updated $it")
        adapter.update(it)
    }


    private fun setupUI() {
        mViewModel.loadMoviesImages(currentMovie.title)

        name.text = "movie title: "+currentMovie.title
        year.text = "movie year: "+currentMovie.year
        if(currentMovie.genres.size > 0) {
            genres.visibility = VISIBLE
            genres.text = "movie genres:\n " + currentMovie.genres
        }else
            genres.visibility = GONE


        if(currentMovie.cast.size > 0) {
            cast.visibility = VISIBLE
            cast.text = "movie cast:\n"+currentMovie.cast
        }else
            cast.visibility = GONE



    }

    private fun setMovieImagesList(){
        adapter = moviePhotosAdapter(mViewModel.moviesImages.value ?: emptyList(),mContext)
        images.layoutManager = GridLayoutManager(activity?.applicationContext!!,2)
        images.adapter = adapter
    }


    override fun onResume() {
        super.onResume()

        setupViewModel()

        setupUI()
        setMovieImagesList()

    }


}