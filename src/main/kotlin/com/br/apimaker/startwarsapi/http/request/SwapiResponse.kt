package com.br.apimaker.startwarsapi.http.request

class SwapiResponse<T> constructor(
    val results: List<T>
)