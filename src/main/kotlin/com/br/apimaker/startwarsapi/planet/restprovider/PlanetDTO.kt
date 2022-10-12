package com.br.apimaker.startwarsapi.planet.restprovider

data class PlanetDTO constructor(
    val name: String,
    val climate: String,
    val terrain: String,
    val films: List<String>? = null
)