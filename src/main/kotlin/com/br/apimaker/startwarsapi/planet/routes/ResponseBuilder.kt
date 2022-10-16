package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.PlanetResponse
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ResponseBuilder {

    fun build(loadPlanetDTO: PlanetResponse): ResponseEntity<List<PlanetDTOOutput>> {
        if (loadPlanetDTO.created)
            return ResponseEntity<List<PlanetDTOOutput>>(loadPlanetDTO.output, HttpStatus.CREATED)

        if(loadPlanetDTO.deleted)
            return ResponseEntity<List<PlanetDTOOutput>>(loadPlanetDTO.output, HttpStatus.NO_CONTENT)

        if (loadPlanetDTO.output.isEmpty())
            return ResponseEntity<List<PlanetDTOOutput>>(null, HttpStatus.NOT_FOUND)

        return ResponseEntity<List<PlanetDTOOutput>>(loadPlanetDTO.output, HttpStatus.OK)
    }
}