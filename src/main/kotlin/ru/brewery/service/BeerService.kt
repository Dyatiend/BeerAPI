package ru.brewery.service

import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import ru.brewery.data.BeerModel

class BeerService {

    private val client = KMongo.createClient()
    private val database = client.getDatabase(DATABASE_NAME)
    private val beerCollection = database.getCollection<BeerModel>(COLLECTION_NAME)

    // Для тестов
    fun findAll(): List<BeerModel> =
        beerCollection.find().toList()

    companion object {

        private const val DATABASE_NAME = "beerDB"
        private const val COLLECTION_NAME = "beers"
    }
}