package com.br.apimaker.startwarsapi.planet.restprovider

data class PlanetResponse(val output: List<PlanetDTOOutput> = listOf(), val created: Boolean = false)