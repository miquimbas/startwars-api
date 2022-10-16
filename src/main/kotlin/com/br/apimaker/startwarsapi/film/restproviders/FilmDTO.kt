package com.br.apimaker.startwarsapi.film.restproviders

import com.google.gson.annotations.SerializedName
import java.util.Date

class FilmDTO constructor(val title: String?, val director: String?, @SerializedName("release_date") val releaseDate: Date?)