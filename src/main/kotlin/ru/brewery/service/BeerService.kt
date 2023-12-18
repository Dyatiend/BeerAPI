package ru.brewery.service

import com.mongodb.client.model.Sorts
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
            Sort.NAME, null -> BeerModel::name.name
            Sort.ABV -> BeerModel::abv.name
            Sort.IBU -> BeerModel::ibu.name
        }

        // Выбираем вариант сортировки
        val sort = when (filter?.order) {
            Order.ASC, null -> Sorts.ascending(sortField)
            Order.DESC -> Sorts.descending(sortField)
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

    fun findStyles(pageKey: Int?, pageSize: Int?): List<StyleModel> {
        return beerCollection
            .distinct(BeerModel::style)
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

    fun createConfig(): ConfigModel {
        val styles = beerCollection.distinct(BeerModel::style)
        return ConfigModel(
            abvMin = styles.minOf { style -> style.abvMin },
            abvMax = styles.maxOf { style -> style.abvMax },
            ibuMin = styles.minOf { style -> style.ibuMin },
            ibuMax = styles.maxOf { style -> style.ibuMax },
        )
    }

    companion object {

        private const val DATABASE_NAME = "beerDB"
        private const val COLLECTION_NAME = "beers"
    }
}