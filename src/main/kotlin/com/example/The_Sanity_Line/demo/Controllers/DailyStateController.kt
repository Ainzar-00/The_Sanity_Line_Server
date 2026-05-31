package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.DailyState
import com.example.The_Sanity_Line.demo.Service.DailyStateService
import com.example.The_Sanity_Line.demo.dtos.DailyStateRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/daily-states")
class DailyStateController(private val service: DailyStateService) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<DailyState>> =
        ResponseEntity.ok(service.getAllByUser(userId))

    @GetMapping("/user/{userId}/date/{date}")
    fun getByDate(
        @PathVariable userId: String,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
    ): ResponseEntity<DailyState> =
        service.getByUserAndDate(userId, date)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun save(@RequestBody request: DailyStateRequest): ResponseEntity<DailyState> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.saveOrUpdate(request))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Unit> {
        service.deleteDailyState(id)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "Not found")))
}