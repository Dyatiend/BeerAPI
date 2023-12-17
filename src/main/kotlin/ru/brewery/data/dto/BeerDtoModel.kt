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
    val isOrganic: Boolean,
    val isRetired: Boolean,
    val labels: LabelsDtoModel,
    val style: StyleDtoModel,
)

fun BeerDtoModel.toBeer(): BeerModel {
    return BeerModel(
        name = name,
        description = description,
        abv = abv,
        ibu = ibu,
        isOrganic = if (isOrganic) "Y" else "N",
        isRetired = if (isRetired) "Y" else "N",
        labels = labels.toLabels(),
        style = style.toStyle(),
    )
}
