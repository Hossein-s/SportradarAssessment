package dev.hosi.sportradarservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SportradarServiceApplication

fun main(args: Array<String>) {
    runApplication<SportradarServiceApplication>(*args)
}
