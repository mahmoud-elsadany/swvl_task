package com.mahmoudelsadany.swvltask.repositories.moviesRepository

import android.content.Context
import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.mahmoudelsadany.swvltask.model.localMovies.baseMovies
import com.mahmoudelsadany.swvltask.model.localMovies.movie
import java.io.IOException

class getLocalMovies {

    companion object{

        fun getMovies(context: Context): List<movie>{
            val jsonFileString = getJsonDataFromAsset(context, "movies.json")
            Log.i("data", jsonFileString+"")

            val gson = Gson()
            val listMoviesType = object : TypeToken<baseMovies>() {}.type

            var listMovies: baseMovies = gson.fromJson(jsonFileString, listMoviesType)
            listMovies.movies.forEachIndexed { idx, movie -> Log.i("data", "> Item $idx:\n$movie") }


            return listMovies.movies
        }

        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

    }
}