package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.log.LogManager
import com.br.apimaker.startwarsapi.planet.PlanetTestProvider.Companion.createDTOInput
import com.br.apimaker.startwarsapi.planet.PlanetTestProvider.Companion.createFilmDTO
import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput

class PlanetServiceTestable(builder: RetrofitBuilder, planetMapper: PlanetMapper, logManager: LogManager,
                            planetRepository: PlanetRepository
)
    : PlanetService(builder, planetMapper, logManager, planetRepository, ) {

    override fun doRequestPlanet(id: Int): PlanetDTOInput? = createDTOInput()

    override fun doRequestFilm(it: String) = createFilmDTO()
}