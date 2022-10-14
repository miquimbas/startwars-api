package com.br.apimaker.startwarsapi.planet.database

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.lang.Nullable

interface PlanetRepository: MongoRepository<PlanetModel, String> {
    @Nullable
    fun findByName(name: String): PlanetModel?
}