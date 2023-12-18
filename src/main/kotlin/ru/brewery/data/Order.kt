package ru.brewery.data

enum class Order(val value: String) {
    ASC("asc"),
    DESC("desc");

    companion object {

        fun fromValue(value: String?): Order {
            return entries.firstOrNull { entry ->
                entry.value == value
            } ?: ASC
        }

        fun allValues(): List<String> {
            return entries.map(Order::value)
        }
    }
}