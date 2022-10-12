package com.br.apimaker.startwarsapi.http.request

import retrofit2.Call
import retrofit2.http.GET

interface PlanetProvider {
    @GET("planets")
    fun getPlanets(): Call<SwapiResponse>
}