package com.br.apimaker.startwarsapi.film.restproviders

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FilmProvider {
    @GET
    fun getFilmByUrl(@Url url: String): Call<FilmDTO>
}