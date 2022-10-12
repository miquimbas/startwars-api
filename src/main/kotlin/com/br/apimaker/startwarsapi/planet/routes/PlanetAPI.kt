package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTO
import com.br.apimaker.startwarsapi.planet.services.PlanetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/planet")
class PlanetAPI @Autowired constructor(
    private val planetService: PlanetService
) {
    @GetMapping("/")
    fun findAll(): List<PlanetDTO>? = planetService.list()

    @PostMapping("/populate-database")
    fun populateDatabase() = planetService.populatePlanetsDatabase()
}