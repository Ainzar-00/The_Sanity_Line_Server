package com.example.The_Sanity_Line.demo.dtos

import com.example.The_Sanity_Line.demo.Entities.*
import tools.jackson.databind.JsonNode
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

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
    val userId: String,
    val age: Int? = null,
    val sex: Sex? = null,
    val weightKg: BigDecimal? = null,
    val baselineMood: Int? = null,
    val stressLevel: Int? = null,
    val digestiveConditions: JsonNode? = null,
    val foodSensitivities: JsonNode? = null,
    val finishedOnboarding: JsonNode? = null,       // ← added
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
    val notes: String? = null,
)

data class OnboardingProgressRequest(               // ← added
    // Full cumulative list of completed sections up to this point,
    // e.g. ["app_intro", "nutrition", "sleep"]
    val finishedOnboarding: List<String>,
    // Only sent by the client when the last pillar is completed
    val onboardingCompletedAt: LocalDateTime? = null,
)

data class DailyStateRequest(
    val userId: String,
    val date: LocalDate,
    val sleepHoursPrevNight: BigDecimal? = null,
    val sleepQuality: Int? = null,
    val wokeUpFeeling: Int? = null,
    val physicalTraining: Boolean = false,
    val trainingType: String? = null,
    val trainingIntensity: Int? = null,
    val trainingDurationMin: Int? = null,
    val stressLevel: Int? = null,
    val currentMood: Int? = null,
    val hungerLevel: Int? = null,
    val energyLevel: Int? = null,
)

data class MealSuggestionRequest(
    val suggestionId: String,
    val userId: String,
    val stateId: String? = null,

    val mealSlot: MealSlot,
    val mealName: String?,

    val ingredients: List<String>?,
    val instructions: String?,
    val gutBrainRationale: String?,

    val targetsCondition: String?,

    val plantSpeciesCount: Int?,
    val fermentedServings: Double,
    val prebioticFiberG: Double?,
    val omega3G: Double?,
    val magnesiumMg: Double?,
    val tryptophanMg: Double?,

    val promptSnapshot: Map<String, Any>?,
    val rawResponseJson: Any?
)

data class MealSuggestionFeedbackRequest(
    val userAccepted: Boolean
)

data class MealLogRequest(
    val userId: String,
    val suggestionId: String? = null,
    val date: LocalDate,
    val mealSlot: MealSlot,
    val plantSpeciesCount: Int? = null,
    val fermentedServings: BigDecimal? = null,
    val prebioticFiberG: BigDecimal? = null,
    val omega3G: BigDecimal? = null,
    val magnesiumMg: BigDecimal? = null,
    val tryptophanMg: BigDecimal? = null,
    val plantSpeciesList: JsonNode? = null,
    val postMealBloating: Int? = null,
    val postMealEnergy: Int? = null,
    val postMealMood: Int? = null,
    val triggerFoodFlag: Boolean = false,
    val triggerFoodNote: String? = null,
)

data class PlantSpeciesLogRequest(
    val userId: String,
    val logId: String,
    val weekStart: LocalDate,
    val speciesName: String,
    val category: PlantCategory,
)

data class DailyNutrientTotalRequest(
    val userId: String,
    val date: LocalDate,
    val plantSpeciesCount: Int? = null,
    val fermentedServings: BigDecimal? = null,
    val prebioticFiberG: BigDecimal? = null,
    val omega3G: BigDecimal? = null,
    val magnesiumMg: BigDecimal? = null,
    val tryptophanMg: BigDecimal? = null,
    val overallScorePct: BigDecimal? = null,
    val targetPlantSpecies: Int? = null,
    val targetFermented: BigDecimal? = null,
    val targetFiberG: BigDecimal? = null,
    val targetOmega3G: BigDecimal? = null,
    val targetMagnesiumMg: BigDecimal? = null,
    val targetTryptophanMg: BigDecimal? = null,
)