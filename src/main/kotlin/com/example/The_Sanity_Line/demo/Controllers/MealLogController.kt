package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.MealLog
import com.example.The_Sanity_Line.demo.Service.MealLogService
import com.example.The_Sanity_Line.demo.dtos.MealLogRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/meal-logs")
class MealLogController(private val service: MealLogService) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<MealLog>> =
        ResponseEntity.ok(service.getAllByUser(userId))

    @GetMapping("/user/{userId}/date/{date}")
    fun getByDate(
        @PathVariable userId: String,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
    ): ResponseEntity<List<MealLog>> =
        ResponseEntity.ok(service.getByUserAndDate(userId, date))

    @PostMapping
    fun save(@RequestBody request: MealLogRequest): ResponseEntity<MealLog> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "Not found")))
}