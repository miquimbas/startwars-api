package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.film.database.FilmModel
import com.br.apimaker.startwarsapi.film.restproviders.FilmProvider
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.log.LogManager
import com.br.apimaker.startwarsapi.planet.restprovider.LoadPlanetDTO
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanetService @Autowired constructor(
    private val builder: RetrofitBuilder,
    private val planetMapper: PlanetMapper,
    private val logManager: LogManager,
    private val planetRepository: PlanetRepository
){
    fun list() = planetRepository.findAll().map { planetMapper.convertToOutputDTO(it) }

    fun findById(id: String) = planetMapper.convertToOutputDTO(planetRepository.findById(id).orElse(null))

    fun load(id: Int): LoadPlanetDTO {
        runCatching {
            return doRequestPlanet(id)
                .takeIf { it != null }
                ?.let(::workPlanet)
                ?: LoadPlanetDTO(null)
        }.onFailure {
            logManager.error(it.stackTraceToString())
            throw it
        }
        return LoadPlanetDTO(null)
    }

    private fun workPlanet(planetDtoInput: PlanetDTOInput) =
        planetRepository.findByName(planetDtoInput.name).takeIf { it != null }
            ?.let {
                LoadPlanetDTO(planetMapper.convertToOutputDTO(it))
            } ?: savePlanet(planetDtoInput)

    private fun savePlanet(planetDTOInput: PlanetDTOInput): LoadPlanetDTO? {
        logManager.info("Saving planet ${planetDTOInput.name}")
        val planetModel = planetRepository.save(
            planetMapper.convertToModel(planetDTOInput, searchFilmes(planetDTOInput.films))
        )
        logManager.info("Planet ${planetDTOInput.name} saved with success")
        return LoadPlanetDTO(planetMapper.convertToOutputDTO(planetModel), true)
    }

    private fun searchFilmes(films: List<String>?): List<FilmModel>? {
        return films?.map {
            val dto = doRequestFilm(it)

            FilmModel(dto?.title, dto?.release_date, dto?.director)
        }
    }

    fun removeById(id: String) {
        runCatching {
            logManager.info("Deleting planet of id $id")
            planetRepository.deleteById(id)
            logManager.info("Planet of id $id deleted with success")
        }.onFailure {
            logManager.error(it.stackTraceToString())
            throw it
        }
    }

    fun findByName(name: String) =
        planetRepository.findByName(name)
            .let { planetMapper.convertToOutputDTO(it) }

    protected fun doRequestPlanet(id: Int) = builder.build<PlanetProvider>(RetrofitBuilder.BASE_URL)
        .searchPlanetById(id)
        .execute()
        .body()

    protected fun doRequestFilm(it: String) = builder.build<FilmProvider>(RetrofitBuilder.BASE_URL)
        .getFilmByUrl(it)
        .execute()
        .body()
}