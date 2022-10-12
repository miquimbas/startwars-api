package com.br.apimaker.startwarsapi.routes

import com.br.apimaker.startwarsapi.http.request.PlanetDTO
import com.br.apimaker.startwarsapi.http.request.PlanetProvider
import com.br.apimaker.startwarsapi.http.request.RetrofitBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/planet")
class PlanetAPI @Autowired constructor(private val builder: RetrofitBuilder) {

    @GetMapping("/")
    fun findAll(): List<PlanetDTO>? {
        return builder.build<PlanetProvider>(RetrofitBuilder.BASE_URL)
            .getPlanets()
            .execute()
            .body()
            ?.results
    }
}