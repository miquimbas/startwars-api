package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.film.database.FilmModel
import com.br.apimaker.startwarsapi.film.restproviders.FilmDTO
import com.br.apimaker.startwarsapi.planet.database.PlanetModel
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

class PlanetMapperTest {

    private val planetMapper = PlanetMapper()

    @Test
    fun `should match variables of model when output dto is parsed`() {
        val planetDTOInput = PlanetDTOInput("1", "name", "climate", "terrain", listOf("url1", "url2"))
        val planetDTOModel = planetMapper.convertToModel(planetDTOInput, null)
        assertThat(planetDTOInput.name).isEqualTo(planetDTOModel.name)
        assertThat(planetDTOInput.climate).isEqualTo(planetDTOModel.climate)
        assertThat(planetDTOInput.terrain).isEqualTo(planetDTOModel.terrain)
    }

    @Test
    fun `should match variables of output dto when model is parsed`() {
        val planetModel = PlanetModel("1", "name", "climate", "terrain", filmModelGenerator(Date()))
        val planetDTO = planetMapper.convertToOutputDTO(planetModel)
        assertThat(planetModel.name).isEqualTo(planetDTO.name)
        assertThat(planetModel.climate).isEqualTo(planetDTO.climate)
        assertThat(planetModel.terrain).isEqualTo(planetDTO.terrain)
        assertThat(planetModel.films?.get(0)?.title).isEqualTo(planetDTO.films?.get(0)?.title)
    }

    private fun filmModelGenerator(today: Date) = listOf(
        FilmModel("title1", today, "director1"),
        FilmModel("title2", today, "director2")
    )

    private fun filmDTOGenerator(today: Date): List<FilmDTO> = listOf(
        FilmDTO("title1", "director1", today),
        FilmDTO("title2", "director2", today)
    )
}