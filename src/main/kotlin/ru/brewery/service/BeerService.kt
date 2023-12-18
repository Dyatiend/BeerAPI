package ru.brewery.service

import org.bson.conversions.Bson
import org.litote.kmongo.*
import ru.brewery.data.*

class BeerService {

    private val client = KMongo.createClient()
    private val database = client.getDatabase(DATABASE_NAME)
    private val beerCollection = database.getCollection<BeerModel>(COLLECTION_NAME)

    fun findBeers(filter: FilterModel?, pageKey: Int?, pageSize: Int?): List<BeerModel> {
        // Список всех фильтров, которые будут применены к запросу
        val filters = mutableListOf<Bson>()

        // Применяем поисковой запрос
        filter?.query?.let { query ->
            // Ищем совпадения в названии пива, а также в названии стиля
            filters.add(
                or(
                    (BeerModel::name).regex(Regex.escape(query), "i"),
                    (BeerModel::style / StyleModel::name).regex(Regex.escape(query), "i"),
                )
            )
        }

        // Применяем фильтр по стилям
        filter?.styles?.let { styles ->
            filters.add(BeerModel::style / StyleModel::id `in` styles)
        }

        // Применяем минимальную границу крепкости
        filter?.abvMin?.let { abvMin ->
            filters.add(BeerModel::abv gte abvMin)
        }

        // Применяем максимальную границу крепкости
        filter?.abvMax?.let { abvMax ->
            filters.add(BeerModel::abv lte abvMax)
        }

        // Применяем минимальную границу горечи
        filter?.ibuMin?.let { ibuMin ->
            filters.add(BeerModel::ibu gte ibuMin)
        }

        // Применяем максимальную границу горечи
        filter?.ibuMax?.let { ibuMax ->
            filters.add(BeerModel::ibu lte ibuMax)
        }

        // Применяем флаг isOrganic
        filter?.isOrganic?.let { isOrganic ->
            filters.add(BeerModel::isOrganic eq if (isOrganic) "Y" else "N")
        }

        // Применяем флаг isRetired
        filter?.isRetired?.let { isRetired ->
            filters.add(BeerModel::isRetired eq if (isRetired) "Y" else "N")
        }

        // Выбираем поле сортировки
        val sortField = when (filter?.sort) {
            Sort.NAME, null -> BeerModel::name
            Sort.ABV -> BeerModel::abv
            Sort.IBU -> BeerModel::ibu
        }

        // Выбираем вариант сортировки
        val sort = when (filter?.order) {
            Order.ASC, null -> ascending(sortField)
            Order.DESC -> descending(sortField)
        }

        // Выполняем запрос
        return beerCollection
            .find(and(filters))
            .sort(sort)
            .apply {
                // Если переданы параметры пагинации
                if (pageKey != null && pageSize != null) {
                    // Передаем часть данных
                    skip(pageKey)
                    limit(pageSize)
                }
            }
            .toList()
    }

    fun findStyles(): List<StyleModel> {
        return beerCollection
            .distinct(BeerModel::style)
            .toList()
    }

    fun createConfig(): ConfigModel {
        val abvMin = beerCollection
            .find(BeerModel::abv ne null)
            .sort(ascending(BeerModel::abv))
            .limit(1).firstNotNullOf(BeerModel::abv)
        val abvMax = beerCollection
            .find(BeerModel::abv ne null)
            .sort(descending(BeerModel::abv))
            .limit(1).firstNotNullOf(BeerModel::abv)
        val ibuMin = beerCollection
            .find(BeerModel::ibu ne null)
            .sort(ascending(BeerModel::ibu))
            .limit(1).firstNotNullOf(BeerModel::ibu)
        val ibuMax = beerCollection
            .find(BeerModel::ibu ne null)
            .sort(descending(BeerModel::ibu))
            .limit(1).firstNotNullOf(BeerModel::ibu)

        return ConfigModel(
            abvMin = abvMin,
            abvMax = abvMax,
            ibuMin = ibuMin,
            ibuMax = ibuMax,
        )
    }

    companion object {

        private const val DATABASE_NAME = "beerDB"
        private const val COLLECTION_NAME = "beers"
    }
}