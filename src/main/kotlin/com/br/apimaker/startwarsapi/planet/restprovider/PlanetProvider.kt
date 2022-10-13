package com.br.apimaker.startwarsapi.planet.restprovider

import com.br.apimaker.startwarsapi.http.request.SwapiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetProvider {
    @GET("planets")
    fun getPlanets(): Call<SwapiResponse<PlanetDTOInput>>

    @GET("planets/{id}")
    fun searchPlanetById(@Path("id") id: Int): Call<PlanetDTOInput>
}