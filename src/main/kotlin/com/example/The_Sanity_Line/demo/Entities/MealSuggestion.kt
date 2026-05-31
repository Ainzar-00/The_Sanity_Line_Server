package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import org.hibernate.annotations.CreationTimestamp
import tools.jackson.databind.JsonNode

@Entity
@Table(name = "meal_suggestions")
data class MealSuggestion(

    @Id
    @Column(name = "suggestion_id", length = 36)
    val suggestionId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    val dailyState: DailyState? = null,

    @CreationTimestamp
    @Column(name = "requested_at", nullable = false, updatable = false)
    val requestedAt: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_slot", nullable = false)
    val mealSlot: MealSlot,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "prompt_snapshot", columnDefinition = "json")
    val promptSnapshot: JsonNode? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "raw_response_json", columnDefinition = "json")
    val rawResponseJson: JsonNode? = null,

    @Column(name = "meal_name", length = 255)
    val mealName: String? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "ingredients", columnDefinition = "json")
    val ingredients: JsonNode? = null,

    @Column(columnDefinition = "TEXT")
    val instructions: String? = null,

    @Column(name = "gut_brain_rationale", columnDefinition = "TEXT")
    val gutBrainRationale: String? = null,

    @Column(name = "targets_condition")
    val targetsCondition: String? = null,

    @Column(name = "plant_species_count")
    val plantSpeciesCount: Int? = null,

    @Column(name = "fermented_servings", precision = 6, scale = 2)
    val fermentedServings: BigDecimal? = null,

    @Column(name = "prebiotic_fiber_g", precision = 8, scale = 2)
    val prebioticFiberG: BigDecimal? = null,

    @Column(name = "omega3_g", precision = 8, scale = 2)
    val omega3G: BigDecimal? = null,

    @Column(name = "magnesium_mg", precision = 8, scale = 2)
    val magnesiumMg: BigDecimal? = null,

    @Column(name = "tryptophan_mg", precision = 8, scale = 2)
    val tryptophanMg: BigDecimal? = null,

    @Column(name = "user_accepted", nullable = false)
    val userAccepted: Boolean = false
)