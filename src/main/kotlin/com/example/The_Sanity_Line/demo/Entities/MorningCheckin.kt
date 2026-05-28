package com.example.The_Sanity_Line.demo.Entities

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "morning_checkin", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "date"])])
data class MorningCheckin(
    @Id
    @Column(name = "checkin_id", length = 36)
    val checkinId: String = UUID.randomUUID().toString(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val date: LocalDate,

    @Column(name = "bloating")
    val bloating: Int? = null,

    @Column(name = "digestion_quality")
    val digestionQuality: Int? = null,

    @Column(name = "energy")
    val energy: Int? = null,

    @Column(name = "mood")
    val mood: Int? = null,

    @Column(name = "stool_form_bss")
    val stoolFormBss: Int? = null,

    @Column(columnDefinition = "TEXT")
    val notes: String? = null,

    @CreationTimestamp
    @Column(name = "recorded_at", nullable = false, updatable = false)
    val recordedAt: LocalDateTime? = null
)
