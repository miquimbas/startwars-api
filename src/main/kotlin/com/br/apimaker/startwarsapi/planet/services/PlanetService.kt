package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.film.database.FilmModel
import com.br.apimaker.startwarsapi.film.restproviders.FilmProvider
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanetService @Autowired constructor(
    private val builder: RetrofitBuilder,
    private val planetMapper: PlanetMapper
){
    @Autowired
    private lateinit var planetRepository: PlanetRepository

    fun list() = planetRepository.findAll().map { planetMapper.convertToOutputDTO(it) }

//    Alterar retorno para dto
    fun findById(id: String) = planetMapper.convertToOutputDTO(planetRepository.findById(id).orElse(null))

    fun load(id: Int): PlanetDTOOutput? {
        return builder.build<PlanetProvider>(RetrofitBuilder.BASE_URL)
            .searchPlanetById(id)
            .execute()
            .body()
            .takeIf { it != null }
            ?.let { planetDtoInput ->
                planetRepository.findByName(planetDtoInput.name).takeIf { it != null }
                    ?.let { planetMapper.convertToOutputDTO(it) }
                    ?: savePlanet(planetDtoInput)
            }
    }

    private fun savePlanet(planetDTOInput: PlanetDTOInput): PlanetDTOOutput {
        val planetModel = planetRepository.save(
            planetMapper.convertToModel(planetDTOInput, searchFilmes(planetDTOInput.films))
        )

        return planetMapper.convertToOutputDTO(planetModel)
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

    fun findByName(name: String) =
        planetRepository.findByName(name)
            .let { planetMapper.convertToOutputDTO(it) }

}