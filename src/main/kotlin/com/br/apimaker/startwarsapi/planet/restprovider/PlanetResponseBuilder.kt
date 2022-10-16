package com.br.apimaker.startwarsapi.planet.restprovider

class PlanetResponseBuilder {

    private lateinit var output: List<PlanetDTOOutput?>
    private var created = false

    companion object {
        fun newInstance() = PlanetResponseBuilder()
    }

    fun withDTOOutput(output: List<PlanetDTOOutput?>): PlanetResponseBuilder {
        this.output = output
        return this
    }

    fun withCreated(created: Boolean): PlanetResponseBuilder {
        this.created = created
        return this
    }

    fun build() =
        PlanetResponse(
            output = this.output.mapNotNull { it },
            created = this.created
        )
}