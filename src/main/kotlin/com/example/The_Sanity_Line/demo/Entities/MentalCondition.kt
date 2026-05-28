package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import tools.jackson.databind.JsonNode

@Entity
@Table(name = "mental_conditions", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "condition_name"])])
data class MentalCondition(
    @Id
    @Column(name = "condition_id", length = 36)
    val conditionId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "condition_name", nullable = false)
    val conditionName: String,

    @Column(name = "severity")
    val severity: Int? = null,

    @Column(name = "duration_months")
    val durationMonths: Int? = null,

    @Convert(converter = JsonNodeConverter::class)
    @Column(name = "priority_nutrients", columnDefinition = "json")
    val priorityNutrients: JsonNode? = null,

    @Column(name = "restrict_caffeine", nullable = false)
    val restrictCaffeine: Boolean = false,

    @Column(name = "restrict_sugar", nullable = false)
    val restrictSugar: Boolean = false,

    @Column(name = "add_vitamin_d", nullable = false)
    val addVitaminD: Boolean = false,

    @Column(name = "add_adaptogens", nullable = false)
    val addAdaptogens: Boolean = false,

    @Column(name = "gradual_fiber", nullable = false)
    val gradualFiber: Boolean = false,

    @Column(name = "active", nullable = false)
    val active: Boolean = true,

    @CreationTimestamp
    @Column(name = "set_at", nullable = false, updatable = false)
    val setAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null
)
