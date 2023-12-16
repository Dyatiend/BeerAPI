package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.LabelsModel

@Serializable
data class LabelsDtoModel(
    val icon: String,
    val medium: String,
    val large: String,
)

fun LabelsDtoModel.toLabels(): LabelsModel {
    return LabelsModel(
        icon = icon,
        medium = medium,
        large = large,
    )
}
