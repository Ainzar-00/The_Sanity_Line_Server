package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.MealSuggestion
import com.example.The_Sanity_Line.demo.Service.MealSuggestionService
import com.example.The_Sanity_Line.demo.dtos.MealSuggestionFeedbackRequest
import com.example.The_Sanity_Line.demo.dtos.MealSuggestionRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/meal-suggestions")
class MealSuggestionController(
    private val service: MealSuggestionService
) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<MealSuggestion>> {
        return ResponseEntity.ok(service.getAllByUser(userId))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<MealSuggestion> {
        val suggestion = service.getById(id)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(suggestion)
    }

    @PostMapping
    fun save(@RequestBody request: MealSuggestionRequest): ResponseEntity<MealSuggestion> {
        val saved = service.saveOrUpdate(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    @PatchMapping("/{id}/feedback")
    fun updateFeedback(
        @PathVariable id: String,
        @RequestBody request: MealSuggestionFeedbackRequest
    ): ResponseEntity<MealSuggestion> {
        val updated = service.updateFeedback(id, request)
        return ResponseEntity.ok(updated)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "Not found")))
    }
}
