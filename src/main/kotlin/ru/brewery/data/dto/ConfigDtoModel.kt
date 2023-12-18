package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.ConfigModel

@Serializable
data class ConfigDtoModel(
    val sortValues: List<String>,
    val orderValues: List<String>,
    val abvMin: Double,
    val abvMax: Double,
    val ibuMin: Double,
    val ibuMax: Double,
)

fun ConfigDtoModel.toConfig(): ConfigModel {
    return ConfigModel(
        abvMin = abvMin,
        abvMax = abvMax,
        ibuMin = ibuMin,
        ibuMax = ibuMax,
    ).also { config ->
        require(config.sortValues == sortValues && config.orderValues == orderValues) {
            "Incorrect sorts and order variants in config"
        }
    }
}
