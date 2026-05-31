package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "daily_state") // removed UniqueConstraint — multiple requests per day allowed
data class DailyState(

    @Id
    @Column(name = "state_id", length = 36)
    val stateId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val date: LocalDate,

    // ---- SLEEP ----

    @Column(name = "sleep_hours_prev_night", precision = 4, scale = 2)
    val sleepHoursPrevNight: BigDecimal? = null,

    @Column(name = "sleep_quality")
    val sleepQuality: Int? = null,              // 1–5

    @Column(name = "woke_up_feeling")
    val wokeUpFeeling: Int? = null,             // 1–5

    // ---- PHYSICAL ACTIVITY ----

    @Column(name = "physical_training", nullable = false)
    val physicalTraining: Boolean = false,

    @Column(name = "training_type", length = 100)
    val trainingType: String? = null,

    @Column(name = "training_intensity")
    val trainingIntensity: Int? = null,         // 1–5

    @Column(name = "training_duration_min")
    val trainingDurationMin: Int? = null,

    // ---- MENTAL STATE ----

    @Column(name = "stress_level")
    val stressLevel: Int? = null,               // 1–10, real-time (≠ morning baseline)

    @Column(name = "current_mood")
    val currentMood: Int? = null,               // 1–5

    @Column(name = "energy_level")
    val energyLevel: Int? = null,               // 1–5

    // ---- NUTRITION CONTEXT (used by AI meal prompt) ----

    @Column(name = "hunger_level")
    val hungerLevel: Int? = null,               // 1–5 (fixed: was hunger_level with underscore)

    @Column(name = "water_intake_ml")
    val waterIntakeMl: Int? = null,             // ml consumed so far today
    // low hydration → AI avoids high-fiber suggestions

    @Column(name = "caffeine_today_mg")
    val caffeineTodayMg: Int? = null,           // mg consumed today
    // high caffeine + anxiety condition → AI reduces stimulating foods

    @Column(name = "alcohol_prev_night", nullable = false)
    val alcoholPrevNight: Boolean = false,       // affects gut lining recovery
    // true → AI boosts probiotic priority

    @Column(name = "current_digestion")
    val currentDigestion: Int? = null,          // 1–5, how gut feels RIGHT NOW before meal
    // differs from morning_checkin — this is real-time

    @Column(name = "hours_since_last_meal")
    val hoursSinceLastMeal: Int? = null,        // fasting state shapes what the gut needs
    // e.g. 6+ hours → gentler meal suggestion

    @Column(name = "cook_time_available_min")
    val cookTimeAvailableMin: Int? = null,      // hard constraint on AI suggestions
    // 10 min → no fermented meal prep

    @Column(name = "craving", length = 100)
    val craving: String? = null,                // "something warm", "sweet" — injected into prompt

    @Column(name = "aversion", length = 100)
    val aversion: String? = null,               // "nothing heavy" — injected as exclusion

    @CreationTimestamp
    @Column(name = "requested_at", nullable = false, updatable = false)
    val requestedAt: LocalDateTime? = LocalDateTime.now()// renamed from recordedAt — this is when
    // the user triggered a meal suggestion
)
