package com.farmbusiness

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.scheduling.annotation.EnableAsync


@EnableAsync
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication(Application::class.java).also {
        it.addListeners(ApplicationStartupLogger())
        it.run(*args)
    }
}

class ApplicationStartupLogger : ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
        val activeProfiles = event.environment.activeProfiles
        println("Running: " + java.lang.String.join(", ", *activeProfiles))
    }
}