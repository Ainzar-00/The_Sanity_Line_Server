package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.UserProfile
import com.example.The_Sanity_Line.demo.Service.UserProfileService
import com.example.The_Sanity_Line.demo.dtos.OnboardingProgressRequest
import com.example.The_Sanity_Line.demo.dtos.UserProfileRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/profiles")
class UserProfileController(
    private val service: UserProfileService,
) {

    @GetMapping("/{profileId}")
    fun getByProfileId(@PathVariable profileId: String): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.getByProfileId(profileId))

    @GetMapping("/user/{userId}")
    fun getByUserId(@PathVariable userId: String): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.getByUserId(userId))

    @GetMapping("/onboarded")
    fun getAllOnboarded(): ResponseEntity<List<UserProfile>> =
        ResponseEntity.ok(service.getAllOnboarded())

    @PostMapping
    fun create(@RequestBody request: UserProfileRequest): ResponseEntity<UserProfile> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(request))

    @PutMapping("/user/{userId}")
    fun update(
        @PathVariable userId: String,
        @RequestBody request: UserProfileRequest,
    ): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.update(userId, request))

    @PutMapping("/upsert")
    fun upsert(@RequestBody request: UserProfileRequest): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.upsert(request))

    // Called by the client after each pillar is completed.
    // Body example after 3rd pillar:
    //   { "finishedOnboarding": ["app_intro", "nutrition", "sleep"] }
    // Body example after last pillar:
    //   { "finishedOnboarding": ["app_intro", ..., "avoiding_harmful_substances"],
    //     "onboardingCompletedAt": "2025-05-28T14:30:00" }
    @PatchMapping("/user/{userId}/onboarding-progress")
    fun updateOnboardingProgress(
        @PathVariable userId: String,
        @RequestBody request: OnboardingProgressRequest,
    ): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.updateOnboardingProgress(userId, request))

    @DeleteMapping("/user/{userId}")
    fun delete(@PathVariable userId: String): ResponseEntity<Unit> {
        service.delete(userId)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "Not found")))

    @ExceptionHandler(IllegalStateException::class)
    fun handleConflict(ex: IllegalStateException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(mapOf("error" to (ex.message ?: "Conflict")))
}