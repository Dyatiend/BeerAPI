package ru.brewery.data

import com.fasterxml.jackson.annotation.JsonProperty
import ru.brewery.data.dto.StyleDtoModel

data class StyleModel(
    @JsonProperty(ID) val id: Int,
    @JsonProperty(NAME) val name: String,
    @JsonProperty(DESCRIPTION) val description: String?,
    @JsonProperty(ABV_MIN) val abvMin: Double,
    @JsonProperty(ABV_MAX) val abvMax: Double,
    @JsonProperty(IBU_MIN) val ibuMin: Double,
    @JsonProperty(IBU_MAX) val ibuMax: Double,
) {

    companion object {

        const val ID = "id"
        const val NAME = "shortName"
        const val DESCRIPTION = "description"
        const val ABV_MIN = "abvMin"
        const val ABV_MAX = "abvMax"
        const val IBU_MIN = "ibuMin"
        const val IBU_MAX = "ibuMax"
    }
}

fun StyleModel.toDto(): StyleDtoModel {
    return StyleDtoModel(
        id = id,
        name = name,
        description = description,
        abvMin = abvMin,
        abvMax = abvMax,
        ibuMin = ibuMin,
        ibuMax = ibuMax,
    )
}
