package com.br.apimaker.startwarsapi.planet.restprovider

data class PlanetDTO constructor(
    var id: String? = null,
    val name: String,
    val climate: String,
    val terrain: String,
    val films: List<String>? = null
)