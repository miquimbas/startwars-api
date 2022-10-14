package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.log.LogManager
import com.br.apimaker.startwarsapi.planet.PlanetTestProvider
import com.br.apimaker.startwarsapi.planet.database.PlanetModel
import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PlanetServiceTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var retrofitBuilder: RetrofitBuilder

    @MockK
    private lateinit var planetMapper: PlanetMapper

    @MockK
    private lateinit var logManager: LogManager

    @MockK
    private lateinit var planetRepository: PlanetRepository

    private val planetService = PlanetServiceTestable(retrofitBuilder, planetMapper, logManager, planetRepository)

    private val model = createModel()
    @BeforeEach
    fun setUp() {
        every { logManager.info(any()) } just Runs
        every { logManager.error(any()) } just Runs
        every { planetMapper.convertToModel(any(), any()) } returns model
        every { planetMapper.convertToOutputDTO(model) } returns outputDTO()
        every { planetRepository.save(model) } returns model
    }

    @Test
    fun `should call save when no planet is found in database`() {
        every { planetRepository.findByName(PlanetTestProvider.DTO_INPUT_NAME) } returns null

        planetService.load(1)

        verify(exactly = 1) { planetRepository.save(model) }
    }

    @Test
    fun `should not call save when no planet is found in database`() {
        every { planetRepository.findByName(PlanetTestProvider.DTO_INPUT_NAME) } returns model

        planetService.load(1)

        verify(exactly = 0) { planetRepository.save(model) }
    }

    private fun outputDTO() = PlanetDTOOutput(
        terrain = PlanetTestProvider.DTO_INPUT_terrain,
        climate = PlanetTestProvider.DTO_INPUT_climate,
        films = null,
        name = PlanetTestProvider.DTO_INPUT_NAME
    )

    private fun createModel() = PlanetModel(
        terrain = PlanetTestProvider.DTO_INPUT_terrain,
        climate = PlanetTestProvider.DTO_INPUT_climate,
        films = null,
        name = PlanetTestProvider.DTO_INPUT_NAME
    )
}