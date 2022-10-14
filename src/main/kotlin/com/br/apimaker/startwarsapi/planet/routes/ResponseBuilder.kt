package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.LoadPlanetDTO
import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ResponseBuilder {

    fun build(loadPlanetDTO: LoadPlanetDTO): ResponseEntity<PlanetDTOOutput> {
        if (loadPlanetDTO.created)
            return ResponseEntity<PlanetDTOOutput>(loadPlanetDTO.output, HttpStatus.CREATED)

        if (loadPlanetDTO.output == null)
            return ResponseEntity<PlanetDTOOutput>(null, HttpStatus.NOT_FOUND)

        return ResponseEntity<PlanetDTOOutput>(loadPlanetDTO.output, HttpStatus.OK)
    }
}