package com.example.The_Sanity_Line.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication{

}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
