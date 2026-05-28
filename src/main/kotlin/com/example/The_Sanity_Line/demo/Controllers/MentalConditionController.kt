package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.MentalCondition
import com.example.The_Sanity_Line.demo.Service.MentalConditionService
import com.example.The_Sanity_Line.demo.dtos.MentalConditionRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/mental-conditions")
class MentalConditionController(
    private val service: MentalConditionService,
) {
    @PostMapping
    fun create(@RequestBody request: MentalConditionRequest): ResponseEntity<MentalCondition> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(request))

    @GetMapping("/user/{userId}")
    fun getByUserId(@PathVariable userId: String): ResponseEntity<List<MentalCondition>> =
        ResponseEntity.ok(service.getByUserId(userId))

    @DeleteMapping("/{conditionId}")
    fun delete(@PathVariable conditionId: String): ResponseEntity<Unit> {
        service.delete(conditionId)
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