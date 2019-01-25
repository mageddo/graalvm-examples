package com.mageddo.springbootgraalvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GraalVmStarter

fun main(args: Array<String>) {
	runApplication<com.mageddo.springbootgraalvm.GraalVmStarter>(*args)
}
