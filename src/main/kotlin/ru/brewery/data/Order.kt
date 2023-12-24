package ru.brewery.data

import java.util.*

enum class Order(val value: String) {
    ASC("asc"),
    DESC("desc");

    companion object {

        fun fromValue(value: String?): Order {
            return entries.firstOrNull { entry ->
                entry.value == value?.lowercase(Locale.getDefault())
            } ?: ASC
        }

        fun allValues(): List<String> {
            return entries.map(Order::value)
        }
    }
}