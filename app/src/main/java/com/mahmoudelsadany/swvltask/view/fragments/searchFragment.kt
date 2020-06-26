package com.mahmoudelsadany.swvltask.view.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudelsadany.swvltask.R
import com.mahmoudelsadany.swvltask.customAdapters.search.searchMoviesAdpater
import com.mahmoudelsadany.swvltask.model.localMovies.movie
import com.mahmoudelsadany.swvltask.viewmodel.searchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class searchFragment : Fragment() {

    private lateinit var viewModel: searchViewModel
    private lateinit var adapter: searchMoviesAdpater


    companion object {

        fun newInstance(): searchFragment {
            return searchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this).get(searchViewModel::class.java)
        viewModel.movies.observe(viewLifecycleOwner, renderMovies)

    }

    //observers
    private val renderMovies = Observer<List<movie>> {
        Log.v("swvl", "data updated $it")
        adapter.update(it)
    }

    //ui
    private fun setupUI() {
        adapter = searchMoviesAdpater(viewModel.movies.value ?: emptyList())
        searchList.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        searchList.adapter = adapter
    }


    override fun onResume() {
        super.onResume()

        viewModel.loadMuseums(activity?.applicationContext!!)
    }
}