package ru.brewery.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.brewery.data.BeerModel
import ru.brewery.data.toDto
import ru.brewery.service.BeerService

fun Application.configureRouting() {

    val service = BeerService()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/test") {
            val beers = service.findAll().map(BeerModel::toDto)
            call.respond(beers)
        }
    }
}
