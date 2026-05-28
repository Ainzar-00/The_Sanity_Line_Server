package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import tools.jackson.databind.JsonNode
import java.util.UUID

@Entity
@Table(name = "prompt_context_snapshots")
data class PromptContextSnapshot(
    @Id
    @Column(name = "snapshot_id", length = 36)
    val snapshotId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggestion_id", nullable = false)
    val suggestion: MealSuggestion,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "user_conditions", columnDefinition = "json")
    val userConditions: JsonNode? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "today_state", columnDefinition = "json")
    val todayState: JsonNode? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "today_nutrients_so_far", columnDefinition = "json")
    val todayNutrientsSoFar: JsonNode? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "weekly_plants_eaten", columnDefinition = "json")
    val weeklyPlantsEaten: JsonNode? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "sensitivities", columnDefinition = "json")
    val sensitivities: JsonNode? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_slot", nullable = false)
    val mealSlot: MealSlot,

    @Column(name = "model_used", length = 100)
    val modelUsed: String? = null,

    @Column(name = "tokens_used")
    val tokensUsed: Int? = null
)
