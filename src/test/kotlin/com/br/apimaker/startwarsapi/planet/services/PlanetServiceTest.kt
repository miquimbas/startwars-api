package com.br.apimaker.startwarsapi.planet.services

import com.br.apimaker.startwarsapi.film.restproviders.FilmDTO
import com.br.apimaker.startwarsapi.film.restproviders.FilmProvider
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import com.br.apimaker.startwarsapi.log.LogManager
import com.br.apimaker.startwarsapi.planet.database.PlanetModel
import com.br.apimaker.startwarsapi.planet.database.PlanetRepository
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import net.bytebuddy.matcher.ElementMatchers.any
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

    @RelaxedMockK
    private lateinit var logManager: LogManager

    @MockK
    private lateinit var planetRepository: PlanetRepository

    private val planetService = PlanetServiceTestable(retrofitBuilder, planetMapper, logManager, planetRepository)

    @BeforeEach
    fun setUp() {
        every { logManager.info(any()) } just Runs
        every { logManager.error(any()) } just Runs
    }

    @Test
    fun `should `() {
        val model = createModel()
        every { planetRepository.findByName(PlanetServiceTestable.DTO_INPUT_NAME) } returns null
        every { planetMapper.convertToModel(any(), any()) } returns model
        every { planetMapper.convertToOutputDTO(model) } returns outputDTO()
        every { planetRepository.save(model) } returns model

        planetService.load(1)

        verify(exactly = 1) { planetRepository.save(model) }
    }

    private fun outputDTO() = PlanetDTOOutput(
        terrain = PlanetServiceTestable.DTO_INPUT_terrain,
        climate = PlanetServiceTestable.DTO_INPUT_climate,
        films = null,
        name = PlanetServiceTestable.DTO_INPUT_NAME
    )

    private fun createModel() = PlanetModel(
        terrain = PlanetServiceTestable.DTO_INPUT_terrain,
        climate = PlanetServiceTestable.DTO_INPUT_climate,
        films = null,
        name = PlanetServiceTestable.DTO_INPUT_NAME
    )
}