package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.planet.database.PlanetModel
import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.film.database.FilmModel
import com.br.apimaker.startwarsapi.film.restproviders.FilmProvider
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.planet.restprovider.LoadPlanetDTO
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
        PlanetDTO(
            it.id,
            it.name,
            it.climate,
            it.terrain,
        )
    }

    fun findById(id: String) = planetRepository.findById(id)

    fun load(id: Int): PlanetDTO? {
        return builder.build<PlanetProvider>(RetrofitBuilder.BASE_URL)
            .searchPlanetById(id)
            .execute()
            .body()
            .takeIf { it != null }
            ?.let(::savePlanet)
    }

    private fun savePlanet(planetDTO: PlanetDTO): PlanetDTO {
        val planetModel = planetRepository.save(
            PlanetModel(
                name = planetDTO.name,
                climate = planetDTO.climate,
                terrain = planetDTO.terrain,
                films = searchFilmes(planetDTO.films)
            )
        )

        return planetDTO.apply {
            this.id = planetModel.id
        }
    }

    private fun searchFilmes(films: List<String>?): List<FilmModel>? {
        return films?.map {
            val dto = builder.build<FilmProvider>(RetrofitBuilder.BASE_URL)
                .getFilmByUrl(it)
                .execute()
                .body()

            //arrumar nome da variavel
            FilmModel(dto?.title, dto?.release_date, dto?.director)
        }
    }

    fun removeById(id: String) {
        planetRepository.deleteById(id)
    }
}