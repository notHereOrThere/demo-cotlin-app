package com.example.taskkotlinapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskKotlinAppApplication

fun main(args: Array<String>) {
    runApplication<TaskKotlinAppApplication>(*args)
}
