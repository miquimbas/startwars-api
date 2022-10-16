package com.br.apimaker.startwarsapi.planet.restprovider

class PlanetResponseBuilder {

    private var output = listOf<PlanetDTOOutput?>()
    private var created = false
    private var deleted = false

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

    fun withDeleted(deleted: Boolean): PlanetResponseBuilder {
        this.deleted = deleted
        return this
    }

    fun build() =
        PlanetResponse(
            output = this.output.mapNotNull { it },
            created = this.created,
            deleted = this.deleted
        )
}