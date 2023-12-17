package ru.brewery.data

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import ru.brewery.data.dto.BeerDtoModel

data class BeerModel(
    @BsonId val id: Id<BeerModel>? = null,
    @JsonProperty(NAME) val name: String,
    @JsonProperty(DESCRIPTION) val description: String?,
    @JsonProperty(ABV) val abv: Double,
    @JsonProperty(IBU) val ibu: Double,
    @JsonProperty(IS_ORGANIC) val isOrganic: String,
    @JsonProperty(IS_RETIRED) val isRetired: String,
    @JsonProperty(LABELS) val labels: LabelsModel,
    @JsonProperty(STYLE) val style: StyleModel,
) {

    companion object {

        const val NAME = "nameDisplay"
        const val DESCRIPTION = "description"
        const val ABV = "abv"
        const val IBU = "ibu"
        const val IS_ORGANIC = "isOrganic"
        const val IS_RETIRED = "isRetired"
        const val LABELS = "labels"
        const val STYLE = "style"
    }
}

fun BeerModel.toDto(): BeerDtoModel {
    return BeerDtoModel(
        id = id.toString(),
        name = name,
        description = description,
        abv = abv,
        ibu = ibu,
        isOrganic = isOrganic.contentEquals("Y"),
        isRetired = isRetired.contentEquals("Y"),
        labels = labels.toDto(),
        style = style.toDto(),
    )
}
