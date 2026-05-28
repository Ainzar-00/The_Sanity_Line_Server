package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.MealSuggestion
import com.example.The_Sanity_Line.demo.Service.MealSuggestionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/meal-suggestions")
class MealSuggestionController(private val service: MealSuggestionService) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<MealSuggestion>> =
        ResponseEntity.ok(service.getAllByUser(userId))

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<MealSuggestion> =
        service.getById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun save(@RequestBody suggestion: MealSuggestion): ResponseEntity<MealSuggestion> =
        ResponseEntity.ok(service.save(suggestion))
}
