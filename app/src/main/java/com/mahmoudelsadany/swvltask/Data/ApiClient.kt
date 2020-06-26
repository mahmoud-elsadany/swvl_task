package com.mahmoudelsadany.swvltask.Data

import com.mahmoudelsadany.swvltask.Utils.myUtils
import com.mahmoudelsadany.swvltask.model.moviesImages.movieImages
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {


    private var servicesApiInterface:ServicesApiInterface?=null

    fun build():ServicesApiInterface?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(myUtils.baseURL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface{

        @GET("rest/")
        fun getMovieImages(@Query("method") method: String,
                         @Query("api_key") api_key: String,
                         @Query("format") format: String,
                         @Query("nojsoncallback") nojsoncallback: Int,
                         @Query("text") text: String,
                         @Query("page") page: Int,
                         @Query("per_page") per_page: Int

        ): Call<movieImages>
    }

}