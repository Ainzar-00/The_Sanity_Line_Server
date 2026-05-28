package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "plant_species_log", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "week_start", "species_name"])])
data class PlantSpeciesLog(
    @Id
    @Column(name = "entry_id", length = 36)
    val entryId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", nullable = false)
    val mealLog: MealLog,

    @Column(name = "week_start", nullable = false)
    val weekStart: LocalDate,

    @Column(name = "species_name", nullable = false, length = 255)
    val speciesName: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val category: PlantCategory,

    @CreationTimestamp
    @Column(name = "first_logged_at", nullable = false, updatable = false)
    val firstLoggedAt: LocalDateTime? = null
)
