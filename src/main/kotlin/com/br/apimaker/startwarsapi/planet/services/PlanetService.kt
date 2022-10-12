package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.planet.database.PlanetModel
import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.film.database.FilmModel
import com.br.apimaker.startwarsapi.film.restproviders.FilmProvider
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTO
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanetService @Autowired constructor(
    private val builder: RetrofitBuilder
){
    @Autowired
    private lateinit var planetRepository: PlanetRepository

    fun list() = planetRepository.findAll().map {
        PlanetDTO(name = it.name, climate = it.climate, terrain = it.terrain)
    }

    fun populatePlanetsDatabase() {
        planetRepository.deleteAll()
        builder.build<PlanetProvider>(RetrofitBuilder.BASE_URL)
            .getPlanets()
            .execute()
            .body()
            ?.results
            ?.forEach { planetDTO ->
                println("Saving planet model ${planetDTO.name}")
                planetRepository.save(
                    PlanetModel(
                    name = planetDTO.name,
                    climate = planetDTO.climate,
                    terrain = planetDTO.terrain,
                    films = searchFilmes(planetDTO.films))
                )
            }
        println("End of populating database")
    }

    private fun searchFilmes(films: List<String>?): List<FilmModel>? {
        return films?.map {
            val dto = builder.build<FilmProvider>(RetrofitBuilder.BASE_URL)
                .getFilmByUrl(it)
                .execute()
                .body()

            FilmModel(dto?.title, dto?.release_date, dto?.director)
        }
    }
}