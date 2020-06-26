package com.mahmoudelsadany.swvltask.customAdapters.search

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudelsadany.swvltask.R
import com.mahmoudelsadany.swvltask.model.localMovies.movie
import com.mahmoudelsadany.swvltask.view.activities.mainActivity
import com.mahmoudelsadany.swvltask.view.fragments.detailsFragment
import kotlinx.android.synthetic.main.item_movie.view.*

class searchMoviesAdpater(private var movies: List<movie>, var context : Context) :
    RecyclerView.Adapter<searchMoviesAdpater.MViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        //render
        vh.bind(movies[position],context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun update(data: Any) {
        movies = data as List<movie>
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            var filteredList: MutableList<movie> = arrayListOf()

            if (constraint.isEmpty()) {
                filteredList.addAll(movies)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in movies) {
                    if (item.title.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.count = filteredList.size
            results.values = filteredList

            Log.d("filtering", results.count.toString())

            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            update(filterResults.values)
        }
    }


    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieName_tv: TextView = view.movieName_tv
        private val movieYear_tv: TextView = view.movieYear_tv
        private val movieRate_tv: TextView = view.movieRate_tv

        fun bind(_movie: movie,context: Context) {
            movieName_tv.text = "Name: " + _movie.title
            movieYear_tv.text = "Year: " + _movie.year
            movieRate_tv.text = "Rating: " + _movie.rating
            itemView.setOnClickListener {
                pushToDetailsFragment(context, _movie)
            }
//            Glide.with(imageView.context).load(museum.photo).into(imageView)
        }

        private fun pushToDetailsFragment(context:Context, movieObject:movie) {
            val bundle = Bundle()
            bundle.putSerializable("movie", movieObject)
            (context as mainActivity).pushFragment(
                detailsFragment(),
                "detailsFragment",
                bundle
            )
        }


    }



}