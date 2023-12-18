package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.FilterModel
import ru.brewery.data.Order
import ru.brewery.data.Sort

@Serializable
data class FilterDtoModel(
    val query: String? = null,
    val styles: List<Int>? = null,
    val abvMin: Double? = null,
    val abvMax: Double? = null,
    val ibuMin: Double? = null,
    val ibuMax: Double? = null,
    val isOrganic: Boolean? = null,
    val isRetired: Boolean? = null,
    val sort: String? = null,
    val order: String? = null,
)

fun FilterDtoModel.toFilter(): FilterModel {
    return FilterModel(
        query = query,
        styles = styles,
        abvMin = abvMin,
        abvMax = abvMax,
        ibuMin = ibuMin,
        ibuMax = ibuMax,
        isOrganic = isOrganic,
        isRetired = isRetired,
        sort = Sort.fromValue(sort),
        order = Order.fromValue(order),
    )
}
