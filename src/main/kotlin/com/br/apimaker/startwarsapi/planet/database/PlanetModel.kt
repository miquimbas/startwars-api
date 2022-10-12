package com.br.apimaker.startwarsapi.planet.database

import com.br.apimaker.startwarsapi.film.database.FilmModel
import org.springframework.data.annotation.Id

data class PlanetModel constructor(
    @Id val id: String? = null,
    val name: String,
    val climate: String,
    val terrain: String,
    val films: List<FilmModel>?)