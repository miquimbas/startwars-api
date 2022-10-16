package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import com.br.apimaker.startwarsapi.planet.services.PlanetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//arrumar nome da variavel release_date
// criar doc de testes
// ensinar como ver cobertura de testes
// descrever na documentação o pq das minhas decisões
// add nas docs como rodar o projeto
// detalhar url para requisição e config do /api

@RestController
@RequestMapping("/planet")
class PlanetAPI @Autowired constructor(
    private val planetService: PlanetService,
    private val responseBuilder: ResponseBuilder
) {
    @GetMapping("/")
    fun findAll(): ResponseEntity<List<PlanetDTOOutput>>? = responseBuilder.build(planetService.list())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = responseBuilder.build(planetService.findById(id))

    @GetMapping("/name/{name}")
    fun findByName(@PathVariable name: String) = responseBuilder.build(planetService.findByName(name))

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): ResponseEntity<String> {
        planetService.removeById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/load")
    fun load(@PathVariable id: Int): ResponseEntity<List<PlanetDTOOutput>> {
        val response = planetService.load(id)
        return responseBuilder.build(response)
    }
}