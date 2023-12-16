package ru.brewery.data

import com.fasterxml.jackson.annotation.JsonProperty
import ru.brewery.data.dto.LabelsDtoModel

data class LabelsModel(
    @JsonProperty(ICON) val icon: String,
    @JsonProperty(MEDIUM) val medium: String,
    @JsonProperty(LARGE) val large: String,
) {

    companion object {

        const val ICON = "icon"
        const val MEDIUM = "medium"
        const val LARGE = "large"
    }
}

fun LabelsModel.toDto(): LabelsDtoModel {
    return LabelsDtoModel(
        icon = icon,
        medium = medium,
        large = large,
    )
}
