package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import com.br.apimaker.startwarsapi.planet.services.PlanetService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/planet")
class PlanetAPI @Autowired constructor(
    private val planetService: PlanetService,
    private val responseBuilder: ResponseBuilder
) {
    @GetMapping("/")
    @ApiOperation("Lista todos os planetas salvos na base da aplicação")
    fun findAll(): ResponseEntity<List<PlanetDTOOutput>>? = responseBuilder.build(planetService.list())

    @GetMapping("/{id}")
    @ApiOperation("Busca um planeta pelo ID, que pode ser encontrado na listagem de todos os planetas")
    fun findById(@PathVariable id: String) = responseBuilder.build(planetService.findById(id))

    @GetMapping("/name/{name}")
    @ApiOperation("Busca um planeta pelo seu nome")
    fun findByName(@PathVariable name: String) = responseBuilder.build(planetService.findByName(name))

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta um planeta pelo seu id, que pode ser encontrado na listagem de todos os planetas")
    fun deleteById(@PathVariable id: String) = responseBuilder.build(planetService.deleteById(id))

    @PostMapping("/{id}/load")
    @ApiOperation("Carrega um planeta da api e salva na base da aplicação")
    fun load(@PathVariable id: Int) = responseBuilder.build(planetService.loadFromIntegrationById(id))
}