package com.br.apimaker.startwarsapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StartwarsApiApplication

fun main(args: Array<String>) {
	runApplication<StartwarsApiApplication>(*args)
}
