package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.MorningCheckin
import com.example.The_Sanity_Line.demo.Service.MorningCheckinService
import com.example.The_Sanity_Line.demo.dtos.MorningCheckinRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/morning-checkins")
class MorningCheckinController(private val service: MorningCheckinService) {

    @GetMapping("/user/{userId}")
    fun getAllByUser(@PathVariable userId: String): ResponseEntity<List<MorningCheckin>> =
        ResponseEntity.ok(service.getAllByUser(userId))

    @GetMapping("/user/{userId}/date/{date}")
    fun getByDate(
        @PathVariable userId: String,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
    ): ResponseEntity<MorningCheckin> =
        service.getByUserAndDate(userId, date)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun save(@RequestBody req: MorningCheckinRequest): ResponseEntity<MorningCheckin> {
        val saved = service.saveFromRequest(req)
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
