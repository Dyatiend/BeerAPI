package ru.brewery.data

import com.fasterxml.jackson.annotation.JsonProperty
import ru.brewery.data.dto.StyleDtoModel

data class StyleModel(
    @JsonProperty(ID) val id: Int,
    @JsonProperty(NAME) val name: String,
    @JsonProperty(DESCRIPTION) val description: String?,
) {

    companion object {

        const val ID = "id"
        const val NAME = "shortName"
        const val DESCRIPTION = "description"
    }
}

fun StyleModel.toDto(): StyleDtoModel {
    return StyleDtoModel(
        id = id,
        name = name,
        description = description,
    )
}
