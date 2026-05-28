package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.WeeklySummary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface WeeklySummaryRepository : JpaRepository<WeeklySummary, String> {
    fun findByUserUserIdAndWeekStart(userId: String, weekStart: LocalDate): WeeklySummary?
    fun findAllByUserUserId(userId: String): List<WeeklySummary>
}
