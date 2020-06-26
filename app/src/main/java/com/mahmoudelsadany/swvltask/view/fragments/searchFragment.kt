package com.mahmoudelsadany.swvltask.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudelsadany.swvltask.R
import com.mahmoudelsadany.swvltask.customAdapters.search.searchMoviesAdpater
import com.mahmoudelsadany.swvltask.model.localMovies.movie
import com.mahmoudelsadany.swvltask.viewmodel.searchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class searchFragment : Fragment() {

    private lateinit var viewModel: searchViewModel
    private lateinit var adapter: searchMoviesAdpater
    private lateinit var mContext: Context


    companion object {

        fun newInstance(): searchFragment {
            return searchFragment()
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

        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setMoviesList()
        searchListener()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(searchViewModel::class.java)
        viewModel.movies.observe(viewLifecycleOwner, renderMovies)
    }

    //observers
    private val renderMovies = Observer<List<movie>> {
        Log.v("swvlSearch", "data updated $it")
        adapter.update(it)
    }

    //ui
    private fun setMoviesList() {
        adapter = searchMoviesAdpater(viewModel.movies.value ?: emptyList(),mContext)
        searchList.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        searchList.adapter = adapter
    }

    private fun searchListener() {
        searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 0)
                    viewModel.loadMovies(mContext)
                else
                    adapter.filter.filter(s.toString())
            }

        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadMovies(mContext)
    }
}