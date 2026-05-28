package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import tools.jackson.databind.JsonNode
import tools.jackson.databind.ObjectMapper

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "user_profile")
data class UserProfile(

    @Id
    @Column(name = "profile_id", length = 36, nullable = false, updatable = false)
    val profileId: String = UUID.randomUUID().toString(),

    // 🔥 REAL relationship instead of userId
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "age")
    val age: Int? = null,

    @Enumerated(EnumType.STRING)
    val sex: Sex? = null,

    @Column(name = "weight_kg", precision = 6, scale = 2)
    val weightKg: BigDecimal? = null,

    @Column(name = "baseline_mood")
    val baselineMood: Int? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "digestive_conditions", columnDefinition = "json")
    val digestiveConditions: JsonNode = ObjectMapper().createArrayNode(),

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "food_sensitivities", columnDefinition = "json")
    val foodSensitivities: JsonNode = ObjectMapper().createArrayNode(),

    @Column(name = "initial_plant_diversity")
    val initialPlantDiversity: Int? = null,

    @Column(name = "initial_fermented_servings", precision = 6, scale = 2)
    val initialFermentedServings: BigDecimal? = null,

    @Column(name = "avg_sleep_hours", precision = 4, scale = 2)
    val avgSleepHours: BigDecimal? = null,

    @Column(name = "stress_level")
    val stressLevel: Int? = null,

    @Column(name = "caffeine_daily_mg")
    val caffeineDailyMg: Int? = null,

    @Column(name = "alcohol_weekly_units")
    val alcoholWeeklyUnits: Int? = null,

    @Column(name = "onboarding_completed_at")
    val onboardingCompletedAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class Sex {
    male, female, other, prefer_not_to_say
}