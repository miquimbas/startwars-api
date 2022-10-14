package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.film.restproviders.FilmDTO
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.log.LogManager
import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput

class PlanetServiceTestable(builder: RetrofitBuilder, planetMapper: PlanetMapper, logManager: LogManager,
                            planetRepository: PlanetRepository
)
    : PlanetService(builder, planetMapper, logManager, planetRepository, ) {

    companion object {
        const val DTO_INPUT_NAME = "planet"
        const val DTO_INPUT_climate = "climate"
        const val DTO_INPUT_terrain = "planet"
        const val DTO_INPUT_URL_FILM_1 = "url1"
        const val DTO_INPUT_URL_FILM_2 = "url2"
    }

    override fun doRequestPlanet(id: Int): PlanetDTOInput? = createDTOInput()

    override fun doRequestFilm(it: String) = createFilmDTO()

    private fun createFilmDTO() = FilmDTO(title = "film", director = "director", release_date = null)

    private fun createDTOInput() = PlanetDTOInput(
        terrain = DTO_INPUT_terrain,
        climate = DTO_INPUT_climate,
        films = listOf(DTO_INPUT_URL_FILM_1, DTO_INPUT_URL_FILM_2),
        name = DTO_INPUT_NAME
    )
}