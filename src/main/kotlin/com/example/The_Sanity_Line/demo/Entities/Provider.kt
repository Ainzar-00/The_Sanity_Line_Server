package com.example.The_Sanity_Line.demo.Entities

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class Provider(@JsonValue val value: String) {
    EMAIL("email"),
    GOOGLE("google"),
    APPLE("apple"),
    PHONE("phone");

    companion object {
        @JsonCreator
        @JvmStatic
        fun fromValue(value: String): Provider {
            return entries.firstOrNull {
                it.value.equals(value, ignoreCase = true)
            } ?: throw IllegalArgumentException("Unknown provider: $value")
        }
    }
}
