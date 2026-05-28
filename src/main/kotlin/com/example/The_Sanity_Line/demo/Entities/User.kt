package com.example.The_Sanity_Line.demo.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users") // ⚠️ avoid reserved keyword "user"
data class User(

    @Id
    @Column(name = "user_id", length = 128)
    var userId: String,

    @Column(length = 255)
    var email: String? = null,

    @Column(name = "display_name", length = 255)
    var displayName: String? = null,

    @Column(name = "photo_url", columnDefinition = "TEXT")
    var photoUrl: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var provider: Provider,

    @Column(length = 100)
    var timezone: String? = null,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @Column(name = "last_seen_at")
    var lastSeenAt: LocalDateTime? = null,

    @Column(name = "is_active")
    var isActive: Boolean = true,

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var profile: UserProfile? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var mentalConditions: MutableList<MentalCondition> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var morningCheckins: MutableList<MorningCheckin> = mutableListOf()



) {
    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
    }
}