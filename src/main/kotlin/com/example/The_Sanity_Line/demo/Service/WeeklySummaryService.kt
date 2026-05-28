package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.WeeklySummary
import com.example.The_Sanity_Line.demo.Repository.WeeklySummaryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class WeeklySummaryService(private val repository: WeeklySummaryRepository) {

    fun getByUserAndWeek(userId: String, weekStart: LocalDate): WeeklySummary? =
        repository.findByUserUserIdAndWeekStart(userId, weekStart)

    fun getAllByUser(userId: String): List<WeeklySummary> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun save(summary: WeeklySummary): WeeklySummary =
        repository.save(summary)
}
