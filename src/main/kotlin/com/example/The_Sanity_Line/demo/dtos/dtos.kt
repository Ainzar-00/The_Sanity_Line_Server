package com.example.The_Sanity_Line.demo.dtos

import com.example.The_Sanity_Line.demo.Entities.Sex
import tools.jackson.databind.JsonNode
import java.math.BigDecimal
import java.time.LocalDate

data class UserRequest(
    val userId: String,
    val email: String? = null,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val provider: String,
    val timezone: String? = null,
    val isActive: Boolean = true,
)

data class UserProfileRequest(
    val userId: String,                           // just the ID, not the full entity
    val age: Int? = null,
    val sex: Sex? = null,
    val weightKg: BigDecimal? = null,
    val baselineMood: Int? = null,
    val stressLevel: Int? = null,
    val digestiveConditions: JsonNode? = null,
    val foodSensitivities: JsonNode? = null,
    val initialPlantDiversity: Int? = null,
    val initialFermentedServings: BigDecimal? = null,
    val avgSleepHours: BigDecimal? = null,
    val caffeineDailyMg: Int? = null,
    val alcoholWeeklyUnits: Int? = null,
)

data class MentalConditionRequest(
    val userId: String,
    val conditionName: String,
    val severity: Int? = null,
    val durationMonths: Int? = null,
    val priorityNutrients: JsonNode? = null,
    val restrictCaffeine: Boolean = false,
    val restrictSugar: Boolean = false,
    val addVitaminD: Boolean = false,
    val addAdaptogens: Boolean = false,
    val gradualFiber: Boolean = false,
    val active: Boolean = true,
)

data class MorningCheckinRequest(
    val userId: String,
    val date: LocalDate,

    val bloating: Int? = null,
    val digestionQuality: Int? = null,
    val energy: Int? = null,
    val mood: Int? = null,
    val stoolFormBss: Int? = null,
    val notes: String? = null
)