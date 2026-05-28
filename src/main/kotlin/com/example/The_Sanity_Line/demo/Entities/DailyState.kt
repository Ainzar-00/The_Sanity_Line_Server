package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "daily_state", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "date"])])
data class DailyState(
    @Id
    @Column(name = "state_id", length = 36)
    val stateId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val date: LocalDate,

    @Column(name = "sleep_hours_prev_night", precision = 4, scale = 2)
    val sleepHoursPrevNight: BigDecimal? = null,

    @Column(name = "sleep_quality")
    val sleepQuality: Int? = null,

    @Column(name = "woke_up_feeling")
    val wokeUpFeeling: Int? = null,

    @Column(name = "physical_training", nullable = false)
    val physicalTraining: Boolean = false,

    @Column(name = "training_type", length = 100)
    val trainingType: String? = null,

    @Column(name = "training_intensity")
    val trainingIntensity: Int? = null,

    @Column(name = "training_duration_min")
    val trainingDurationMin: Int? = null,

    @Column(name = "stress_level")
    val stressLevel: Int? = null,

    @Column(name = "current_mood")
    val currentMood: Int? = null,

    @Column(name = "hunger_level")
    val hunger_level: Int? = null,

    @Column(name = "energy_level")
    val energyLevel: Int? = null,

    @CreationTimestamp
    @Column(name = "recorded_at", nullable = false, updatable = false)
    val recordedAt: LocalDateTime? = null
)
