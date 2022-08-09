package xyz.danizz.wdym.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import xyz.danizz.wdym.application.config.ConfigProperties
import xyz.danizz.wdym.data.repository.MemeImagePathRepository

@SpringBootApplication(scanBasePackages = ["xyz.danizz.wdym"])
@EnableConfigurationProperties(ConfigProperties::class)
@EnableMongoRepositories(basePackageClasses = [MemeImagePathRepository::class])
class WdymApplication

fun main(args: Array<String>) {
    runApplication<WdymApplication>(*args)
}
