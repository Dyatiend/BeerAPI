package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.BeerModel

@Serializable
data class BeerDtoModel(
    val id: String? = null,
    val name: String,
    val description: String?,
    val abv: Double,
    val ibu: Double,
    val labels: LabelsDtoModel,
    val style: StyleDtoModel,
)

fun BeerDtoModel.toBeer(): BeerModel {
    return BeerModel(
        name = name,
        description = description,
        abv = abv,
        ibu = ibu,
        labels = labels.toLabels(),
        style = style.toStyle(),
    )
}
