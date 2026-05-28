package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "food_mood_correlations")
data class FoodMoodCorrelation(
    @Id
    @Column(name = "corr_id", length = 36)
    val corrId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @CreationTimestamp
    @Column(name = "computed_at", nullable = false, updatable = false)
    val computedAt: LocalDateTime? = null,

    @Column(name = "window_days", nullable = false)
    val windowDays: Int,

    @Column(nullable = false, length = 50)
    val nutrient: String,

    @Column(nullable = false, length = 50)
    val outcome: String,

    @Column(name = "pearson_r", precision = 8, scale = 6)
    val pearsonR: BigDecimal? = null,

    @Column(name = "p_value", precision = 12, scale = 10)
    val pValue: BigDecimal? = null,

    @Column(name = "lag_days")
    val lagDays: Int? = null,

    @Column(name = "n_samples")
    val nSamples: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('positive','negative','neutral')")
    val direction: CorrelationDirection? = null,

    @Column(name = "insight_text", columnDefinition = "TEXT")
    val insightText: String? = null,

    @Column(name = "shown_to_user", nullable = false)
    val shownToUser: Boolean = false
)
