package ru.brewery.data

import ru.brewery.data.dto.FilterDtoModel

data class FilterModel(
    val query: String?,
    val styles: List<Int>?,
    val abvMin: Double?,
    val abvMax: Double?,
    val ibuMin: Double?,
    val ibuMax: Double?,
    val isOrganic: Boolean?,
    val isRetired: Boolean?,
    val sort: Sort,
    val order: Order,
)

fun FilterModel.toDto(): FilterDtoModel {
    return FilterDtoModel(
        query = query,
        styles = styles,
        abvMin = abvMin,
        abvMax = abvMax,
        ibuMin = ibuMin,
        ibuMax = ibuMax,
        isOrganic = isOrganic,
        isRetired = isRetired,
        sort = sort.value,
        order = order.value,
    )
}
