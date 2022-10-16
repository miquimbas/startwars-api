package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.PlanetTestProvider.Companion.createPlanetDTOOutput
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ResponseBuilderTest {

    private val responseBuilder = ResponseBuilder()

    @Test
    fun `should return http created when a register was created`() {
        val response = responseBuilder.build(PlanetResponse(created = true, output = createPlanetDTOOutput()))

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    fun `should return http not found when planet do not exists`() {
        val response = responseBuilder.build(PlanetResponse(created = false))

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `should return http ok when planet exists but was not created`() {
        val response = responseBuilder.build(PlanetResponse(created = false, output = createPlanetDTOOutput()))

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}