package com.br.apimaker.startwarsapi.planet.database

import org.springframework.data.mongodb.repository.MongoRepository

interface PlanetRepository: MongoRepository<PlanetModel, String>