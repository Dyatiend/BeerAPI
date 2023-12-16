package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.StyleModel

@Serializable
data class StyleDtoModel(
    val id: Int,
    val name: String,
    val description: String?,
    val abvMin: Double,
    val abvMax: Double,
    val ibuMin: Double,
    val ibuMax: Double,
)

fun StyleDtoModel.toStyle(): StyleModel {
    return StyleModel(
        id = id,
        name = name,
        description = description,
        abvMin = abvMin,
        abvMax = abvMax,
        ibuMin = ibuMin,
        ibuMax = ibuMax,
    )
}
