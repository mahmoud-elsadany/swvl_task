package com.mahmoudelsadany.swvltask.model.localMovies

data class movie(
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int,
    val title: String,
    val year: Int
)