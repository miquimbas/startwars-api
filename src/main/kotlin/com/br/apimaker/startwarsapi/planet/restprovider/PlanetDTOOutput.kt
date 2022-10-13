package com.br.apimaker.startwarsapi.planet.restprovider

import com.br.apimaker.startwarsapi.film.restproviders.FilmDTO

data class PlanetDTOOutput constructor(
    var id: String? = null,
    val name: String?,
    val climate: String?,
    val terrain: String?,
    val films: List<FilmDTO>? = null
)