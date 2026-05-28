package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "weekly_summaries", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "week_start"])])
data class WeeklySummary(
    @Id
    @Column(name = "summary_id", length = 36)
    val summaryId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "week_start", nullable = false)
    val weekStart: LocalDate,

    @Column(name = "unique_plant_species")
    val uniquePlantSpecies: Int? = null,

    @Column(name = "avg_fermented_per_day", precision = 6, scale = 2)
    val avgFermentedPerDay: BigDecimal? = null,

    @Column(name = "avg_fiber_g", precision = 8, scale = 2)
    val avgFiberG: BigDecimal? = null,

    @Column(name = "avg_omega3_g", precision = 8, scale = 2)
    val avgOmega3G: BigDecimal? = null,

    @Column(name = "avg_magnesium_mg", precision = 8, scale = 2)
    val avgMagnesiumMg: BigDecimal? = null,

    @Column(name = "avg_tryptophan_mg", precision = 8, scale = 2)
    val avgTryptophanMg: BigDecimal? = null,

    @Column(name = "avg_overall_score", precision = 5, scale = 2)
    val avgOverallScore: BigDecimal? = null,

    @Column(name = "avg_mood", precision = 5, scale = 2)
    val avgMood: BigDecimal? = null,

    @Column(name = "avg_energy", precision = 5, scale = 2)
    val avgEnergy: BigDecimal? = null,

    @Column(name = "avg_bloating", precision = 5, scale = 2)
    val avgBloating: BigDecimal? = null,

    @Column(name = "avg_digestion", precision = 5, scale = 2)
    val avgDigestion: BigDecimal? = null,

    @Column(name = "avg_sleep_hours", precision = 5, scale = 2)
    val avgSleepHours: BigDecimal? = null,

    @Column(name = "training_days")
    val trainingDays: Int? = null,

    @Column(name = "days_logged")
    val daysLogged: Int? = null,

    @CreationTimestamp
    @Column(name = "computed_at", nullable = false, updatable = false)
    val computedAt: LocalDateTime? = null
)
