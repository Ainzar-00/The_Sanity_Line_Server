package com.example.The_Sanity_Line.demo.Controllers


import com.example.The_Sanity_Line.demo.Entities.UserProfile
import com.example.The_Sanity_Line.demo.Service.UserProfileService
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

    // ✅ DTO instead of entity
    @PostMapping
    fun create(@RequestBody request: UserProfileRequest): ResponseEntity<UserProfile> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(request))

    // ✅ DTO instead of entity
    @PutMapping("/user/{userId}")
    fun update(
        @PathVariable userId: String,
        @RequestBody request: UserProfileRequest,
    ): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.update(userId, request))

    // ✅ DTO instead of entity
    @PutMapping("/upsert")
    fun upsert(@RequestBody request: UserProfileRequest): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.upsert(request))

    @PatchMapping("/user/{userId}/onboarding")
    fun completeOnboarding(@PathVariable userId: String): ResponseEntity<UserProfile> =
        ResponseEntity.ok(service.completeOnboarding(userId))

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