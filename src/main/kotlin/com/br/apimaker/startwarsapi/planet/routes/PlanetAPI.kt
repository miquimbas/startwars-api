package com.br.apimaker.startwarsapi.planet.routes

import com.br.apimaker.startwarsapi.planet.restprovider.PlanetDTO
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

// criar doc de testes
// ensinar como ver cobertura de testes
// descrever na documentação o pq das minhas decisões
// ajustar carregamento da app para load
// ajustar retorno de status de erro
//tratar ids invalidos
// add nas docs como rodar o projeto
// detalhar url para requisição e config do /api
//add validação se existe o registro no banco para salvar
@RestController
@RequestMapping("/planet")
class PlanetAPI @Autowired constructor(
    private val planetService: PlanetService
) {
    @GetMapping("/")
    fun findAll(): List<PlanetDTO>? = planetService.list()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = planetService.findById(id)

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
                ResponseEntity<PlanetDTO>(it, HttpStatus.CREATED)
            } ?: ResponseEntity<PlanetDTO>(null, HttpStatus.NOT_FOUND)
}