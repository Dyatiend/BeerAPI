package ru.brewery.data

import ru.brewery.data.dto.StylesResponseDtoModel

data class StylesResponseModel(
    val items: List<StyleModel>,
)

fun StylesResponseModel.toDto(): StylesResponseDtoModel {
    return StylesResponseDtoModel(
        items = items.map(StyleModel::toDto)
    )
}
