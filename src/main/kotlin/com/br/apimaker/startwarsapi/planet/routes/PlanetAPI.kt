package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTOOutput
import com.br.apimaker.startwarsapi.planet.services.PlanetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// add logs
//criar testes
// criar doc de testes
// ensinar como ver cobertura de testes
// descrever na documentação o pq das minhas decisões
// ajustar retorno de status de erro
//tratar ids invalidos
// add nas docs como rodar o projeto
// detalhar url para requisição e config do /api
// ajustar status de erro do {id}/load quando não criamos um registro
//arrumar nome da variavel release_date
@RestController
@RequestMapping("/planet")
class PlanetAPI @Autowired constructor(
    private val planetService: PlanetService
) {
    @GetMapping("/")
    fun findAll(): List<PlanetDTOOutput>? = planetService.list()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = planetService.findById(id)

    @GetMapping("/name/{name}")
    fun findByName(@PathVariable name: String) = planetService.findByName(name)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): ResponseEntity<String> {
        planetService.removeById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/load")
    fun load(@PathVariable id: Int) =
        planetService.load(id)
            .takeIf { it != null }
            ?.let {
                ResponseEntity<PlanetDTOOutput>(it, HttpStatus.CREATED)
            } ?: ResponseEntity<PlanetDTOOutput>(null, HttpStatus.NOT_FOUND)
}