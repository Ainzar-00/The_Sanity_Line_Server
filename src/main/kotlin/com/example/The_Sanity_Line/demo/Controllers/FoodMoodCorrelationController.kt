package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.FoodMoodCorrelation
import com.example.The_Sanity_Line.demo.Service.FoodMoodCorrelationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/correlations")
class FoodMoodCorrelationController(private val service: FoodMoodCorrelationService) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<FoodMoodCorrelation>> =
        ResponseEntity.ok(service.getAllByUser(userId))

    @PostMapping
    fun save(@RequestBody correlation: FoodMoodCorrelation): ResponseEntity<FoodMoodCorrelation> =
        ResponseEntity.ok(service.save(correlation))
}
