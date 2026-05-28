package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.UserProfile
import com.example.The_Sanity_Line.demo.Repository.UserProfileRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.UserProfileRequest

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tools.jackson.databind.ObjectMapper
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class UserProfileService(
    private val repository: UserProfileRepository,
    private val userRepository: UserRepository,
) {

    // ── Queries ───────────────────────────────────────────────────────────────

    fun getByProfileId(profileId: String): UserProfile =
        repository.findById(profileId)
            .orElseThrow { NoSuchElementException("Profile not found: $profileId") }

    fun getByUserId(userId: String): UserProfile =
        repository.findByUser_UserId(userId)
            .orElseThrow { NoSuchElementException("Profile not found for user: $userId") }

    fun existsForUser(userId: String): Boolean =
        repository.existsByUser_UserId(userId)

    fun hasCompletedOnboarding(userId: String): Boolean =
        repository.existsByUser_UserIdAndOnboardingCompletedAtIsNotNull(userId)

    fun getAllOnboarded(): List<UserProfile> =
        repository.findByOnboardingCompletedAtIsNotNull()

    // ── Commands ──────────────────────────────────────────────────────────────

    @Transactional
    fun create(request: UserProfileRequest): UserProfile {
        // ✅ Resolve the real User entity from DB — no deserialization needed
        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User not found: ${request.userId}") }

        if (repository.existsByUser_UserId(request.userId)) {
            throw IllegalStateException("Profile already exists for user: ${request.userId}")
        }

        return repository.save(
            UserProfile(
                user = user,
                age = request.age,
                sex = request.sex,
                weightKg = request.weightKg,
                baselineMood = request.baselineMood,
                stressLevel = request.stressLevel,
                digestiveConditions = ((request.digestiveConditions ?: ObjectMapper().createArrayNode()) ),
                foodSensitivities   = ((request.foodSensitivities   ?: ObjectMapper().createArrayNode())) ,
                initialPlantDiversity = request.initialPlantDiversity,
                initialFermentedServings = request.initialFermentedServings,
                avgSleepHours = request.avgSleepHours,
                caffeineDailyMg = request.caffeineDailyMg,
                alcoholWeeklyUnits = request.alcoholWeeklyUnits,
            )
        )
    }

    @Transactional
    fun update(userId: String, request: UserProfileRequest): UserProfile {
        // ✅ userId is authoritative from the path variable, not the body
        val existing = repository.findByUser_UserId(userId)
            .orElseThrow { NoSuchElementException("Profile not found for user: $userId") }

        return repository.save(
            existing.copy(
                age                      = request.age ?: existing.age,
                sex                      = request.sex ?: existing.sex,
                weightKg                 = request.weightKg ?: existing.weightKg,
                baselineMood             = request.baselineMood ?: existing.baselineMood,
                stressLevel              = request.stressLevel ?: existing.stressLevel,
                digestiveConditions = ((request.digestiveConditions ?: existing.digestiveConditions) ),
                foodSensitivities   = ((request.foodSensitivities   ?: existing.foodSensitivities)) ,
                initialPlantDiversity    = request.initialPlantDiversity ?: existing.initialPlantDiversity,
                initialFermentedServings = request.initialFermentedServings ?: existing.initialFermentedServings,
                avgSleepHours            = request.avgSleepHours ?: existing.avgSleepHours,
                caffeineDailyMg          = request.caffeineDailyMg ?: existing.caffeineDailyMg,
                alcoholWeeklyUnits       = request.alcoholWeeklyUnits ?: existing.alcoholWeeklyUnits,
            )
        )
    }

    @Transactional
    fun upsert(request: UserProfileRequest): UserProfile =
        if (repository.existsByUser_UserId(request.userId)) {
            update(request.userId, request)
        } else {
            create(request)
        }

    @Transactional
    fun completeOnboarding(userId: String): UserProfile {
        val profile = repository.findByUser_UserId(userId)
            .orElseThrow { NoSuchElementException("Profile not found for user: $userId") }

        if (profile.onboardingCompletedAt != null) {
            throw IllegalStateException("Onboarding already completed for user: $userId")
        }

        return repository.save(profile.copy(onboardingCompletedAt = LocalDateTime.now()))
    }

    @Transactional
    fun delete(userId: String) {
        if (!repository.existsByUser_UserId(userId)) {
            throw NoSuchElementException("Profile not found for user: $userId")
        }
        repository.deleteByUser_UserId(userId)
    }
}