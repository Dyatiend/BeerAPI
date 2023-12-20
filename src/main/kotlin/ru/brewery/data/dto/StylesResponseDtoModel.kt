package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.StylesResponseModel

@Serializable
data class StylesResponseDtoModel(
    val items: List<StyleDtoModel>,
)

fun StylesResponseDtoModel.toResponse(): StylesResponseModel {
    return StylesResponseModel(
        items = items.map(StyleDtoModel::toStyle)
    )
}
