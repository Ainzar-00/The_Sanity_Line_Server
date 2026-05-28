package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.DailyNutrientTotal
import com.example.The_Sanity_Line.demo.Service.DailyNutrientTotalService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/nutrient-totals")
class DailyNutrientTotalController(private val service: DailyNutrientTotalService) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<DailyNutrientTotal>> =
        ResponseEntity.ok(service.getAllByUser(userId))

    @GetMapping("/user/{userId}/date/{date}")
    fun getByDate(
        @PathVariable userId: String,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
    ): ResponseEntity<DailyNutrientTotal> =
        service.getByUserAndDate(userId, date)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun save(@RequestBody total: DailyNutrientTotal): ResponseEntity<DailyNutrientTotal> =
        ResponseEntity.ok(service.save(total))
}
