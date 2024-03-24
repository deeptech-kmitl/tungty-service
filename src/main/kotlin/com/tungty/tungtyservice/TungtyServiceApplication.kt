package com.tungty.tungtyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TungtyServiceApplication

fun main(args: Array<String>) {
	runApplication<TungtyServiceApplication>(*args)
}
