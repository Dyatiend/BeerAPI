package ru.brewery.data

enum class Sort(val value: String) {
    NAME("name"),
    ABV("abv"),
    IBU("ibu");

    companion object {

        fun fromValue(value: String?): Sort {
            return entries.firstOrNull { entry ->
                entry.value == value
            } ?: NAME
        }

        fun allValues(): List<String> {
            return entries.map(Sort::value)
        }
    }
}