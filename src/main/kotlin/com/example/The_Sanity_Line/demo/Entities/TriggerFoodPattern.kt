package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "trigger_food_patterns")
data class TriggerFoodPattern(
    @Id
    @Column(name = "pattern_id", length = 36)
    val patternId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "food_name", nullable = false, length = 255)
    val foodName: String,

    @Column(nullable = false)
    val occurrences: Int = 0,

    @Column(name = "avg_bloating_after", precision = 6, scale = 2)
    val avgBloatingAfter: BigDecimal? = null,

    @Column(name = "avg_mood_after", precision = 6, scale = 2)
    val avgMoodAfter: BigDecimal? = null,

    @Column(name = "first_flagged")
    val firstFlagged: LocalDate? = null,

    @Column(name = "last_flagged")
    val lastFlagged: LocalDate? = null
)
