package com.br.apimaker.startwarsapi.planet

import com.br.apimaker.startwarsapi.film.restproviders.FilmDTO
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput

class PlanetTestProvider {

    companion object {
        const val DTO_INPUT_NAME = "planet"
        const val DTO_INPUT_climate = "climate"
        const val DTO_INPUT_terrain = "planet"
        const val DTO_INPUT_URL_FILM_1 = "url1"
        const val DTO_INPUT_URL_FILM_2 = "url2"

        fun createFilmDTO() = FilmDTO(title = "film", director = "director", release_date = null)

        fun createDTOInput() = PlanetDTOInput(
            terrain = DTO_INPUT_terrain,
            climate = DTO_INPUT_climate,
            films = listOf(DTO_INPUT_URL_FILM_1, DTO_INPUT_URL_FILM_2),
            name = DTO_INPUT_NAME
        )

        fun createPlanetDTOOutput() = PlanetDTOOutput(
            terrain = DTO_INPUT_terrain,
            climate = DTO_INPUT_climate,
            films = listOf(createFilmDTO()),
            name = DTO_INPUT_NAME
        )
    }


}