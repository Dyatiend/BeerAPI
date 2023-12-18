package ru.brewery.data

import ru.brewery.data.dto.ConfigDtoModel

data class ConfigModel(
    val abvMin: Double,
    val abvMax: Double,
    val ibuMin: Double,
    val ibuMax: Double,
) {

    val sortValues: List<String> = Sort.allValues()
    val orderValues: List<String> = Order.allValues()
}

fun ConfigModel.toDto(): ConfigDtoModel {
    return ConfigDtoModel(
        sortValues = sortValues,
        orderValues = orderValues,
        abvMin = abvMin,
        abvMax = abvMax,
        ibuMin = ibuMin,
        ibuMax = ibuMax,
    )
}
