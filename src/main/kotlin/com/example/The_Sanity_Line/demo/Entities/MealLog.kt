package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp
import tools.jackson.databind.JsonNode

@Entity
@Table(name = "meal_logs")
data class MealLog(
    @Id
    @Column(name = "log_id", length = 36)
    val logId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggestion_id")
    val suggestion: MealSuggestion? = null,

    @Column(nullable = false)
    val date: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_slot", nullable = false)
    val mealSlot: MealSlot,

    @CreationTimestamp
    @Column(name = "logged_at", nullable = false, updatable = false)
    val loggedAt: LocalDateTime? = null,

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

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "plant_species_list", columnDefinition = "json")
    val plantSpeciesList: JsonNode? = null,

    @Column(name = "trigger_food_flag", nullable = false)
    val triggerFoodFlag: Boolean = false,

    @Column(name = "trigger_food_note", columnDefinition = "TEXT")
    val triggerFoodNote: String? = null
)
