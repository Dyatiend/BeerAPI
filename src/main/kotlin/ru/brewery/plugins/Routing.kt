package ru.brewery.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import ru.brewery.data.dto.ErrorResponse
import ru.brewery.data.dto.FilterDtoModel
import ru.brewery.data.dto.toFilter
import ru.brewery.data.toDto
import ru.brewery.service.BeerService

fun Application.configureRouting() {

    val service = BeerService()

    routing {
        get("/beers") {
            try {
                val filter = call.parameters["filter"]?.let { filterString ->
                    Json.decodeFromString<FilterDtoModel>(filterString)
                }?.toFilter()

                val pageKey = call.parameters["pageKey"]?.toIntOrNull()
                val pageSize = call.parameters["pageSize"]?.toIntOrNull()

                val response = service.findBeers(
                    filter = filter,
                    pageKey = pageKey,
                    pageSize = pageSize,
                ).toDto()

                call.respond(
                    HttpStatusCode.OK,
                    response,
                )
            } catch (_: Exception) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse.BAD_REQUEST_RESPONSE,
                )
            }
        }

        get("/styles") {
            try {
                val response = service.findStyles().toDto()

                call.respond(
                    HttpStatusCode.OK,
                    response,
                )
            } catch (_: Exception) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse.BAD_REQUEST_RESPONSE,
                )
            }
        }

        get("/config") {
            try {
                call.respond(
                    HttpStatusCode.OK,
                    service.createConfig().toDto(),
                )
            } catch (_: Exception) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse.BAD_REQUEST_RESPONSE,
                )
            }
        }
    }
}
