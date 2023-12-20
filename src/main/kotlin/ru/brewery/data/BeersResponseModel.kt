package ru.brewery.data

import ru.brewery.data.dto.BeersResponseDtoModel

data class BeersResponseModel(
    val items: List<BeerModel>,
    val firstPageKey: Int,
    val lastPageKey: Int,
)

fun BeersResponseModel.toDto(): BeersResponseDtoModel {
    return BeersResponseDtoModel(
        items = items.map(BeerModel::toDto),
        firstPageKey = firstPageKey,
        lastPageKey = lastPageKey,
    )
}
