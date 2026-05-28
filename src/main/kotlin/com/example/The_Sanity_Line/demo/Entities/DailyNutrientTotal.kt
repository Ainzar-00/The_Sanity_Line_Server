package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "daily_nutrient_totals", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "date"])])
data class DailyNutrientTotal(
    @Id
    @Column(name = "total_id", length = 36)
    val totalId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val date: LocalDate,

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

    @Column(name = "overall_score_pct", precision = 5, scale = 2)
    val overallScorePct: BigDecimal? = null,

    @Column(name = "target_plant_species")
    val targetPlantSpecies: Int? = null,

    @Column(name = "target_fermented", precision = 6, scale = 2)
    val targetFermented: BigDecimal? = null,

    @Column(name = "target_fiber_g", precision = 8, scale = 2)
    val targetFiberG: BigDecimal? = null,

    @Column(name = "target_omega3_g", precision = 8, scale = 2)
    val targetOmega3G: BigDecimal? = null,

    @Column(name = "target_magnesium_mg", precision = 8, scale = 2)
    val targetMagnesiumMg: BigDecimal? = null,

    @Column(name = "target_tryptophan_mg", precision = 8, scale = 2)
    val targetTryptophanMg: BigDecimal? = null,

    @CreationTimestamp
    @Column(name = "computed_at", nullable = false, updatable = false)
    val computedAt: LocalDateTime? = null
)
