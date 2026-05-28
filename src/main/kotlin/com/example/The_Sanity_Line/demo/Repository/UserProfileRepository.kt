package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserProfileRepository : JpaRepository<UserProfile, String> {

    /** Find by the external auth user_id. */
    fun findByUser_UserId(userId: String): Optional<UserProfile>

    /** Check whether a profile already exists for a given user. */
    fun existsByUser_UserId(userId: String): Boolean

    /** Find all profiles that have completed onboarding. */
    fun findByOnboardingCompletedAtIsNotNull(): List<UserProfile>

    /** Delete by auth user_id (e.g. account deletion). */
    fun deleteByUser_UserId(userId: String)

    /** Lightweight existence check before creating a duplicate. */
    fun existsByUser_UserIdAndOnboardingCompletedAtIsNotNull(userId: String): Boolean
}