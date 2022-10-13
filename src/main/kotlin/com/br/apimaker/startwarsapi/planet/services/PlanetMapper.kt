package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.film.database.FilmModel
import com.br.apimaker.startwarsapi.film.restproviders.FilmDTO
import com.br.apimaker.startwarsapi.planet.database.PlanetModel
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import org.springframework.stereotype.Service

@Service
class PlanetMapper {

    fun convertToOutputDTO(planetModel: PlanetModel?) = PlanetDTOOutput(
        planetModel?.id,
        planetModel?.name,
        planetModel?.climate,
        planetModel?.terrain,
        convertToFilmDTO(planetModel?.films)
    )

    fun convertToModel(planetDTOInput: PlanetDTOInput, films: List<FilmModel>?) = PlanetModel(
        name = planetDTOInput.name,
        climate = planetDTOInput.climate,
        terrain = planetDTOInput.terrain,
        films = films
    )

    private fun convertToFilmDTO(films: List<FilmModel>?) = films?.map {
        FilmDTO(
            it.title,
            it.director,
            it.releaseDate
        )
    }
}