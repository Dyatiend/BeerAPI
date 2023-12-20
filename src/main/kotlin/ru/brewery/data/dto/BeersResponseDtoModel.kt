package ru.brewery.data.dto

import kotlinx.serialization.Serializable
import ru.brewery.data.BeersResponseModel

@Serializable
data class BeersResponseDtoModel(
    val items: List<BeerDtoModel>,
    val firstPageKey: Int,
    val lastPageKey: Int,
)

fun BeersResponseDtoModel.toResponse(): BeersResponseModel {
    return BeersResponseModel(
        items = items.map(BeerDtoModel::toBeer),
        firstPageKey = firstPageKey,
        lastPageKey = lastPageKey,
    )
}
