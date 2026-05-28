package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.DailyNutrientTotal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyNutrientTotalRepository : JpaRepository<DailyNutrientTotal, String> {
    fun findByUserUserIdAndDate(userId: String, date: LocalDate): DailyNutrientTotal?
    fun findAllByUserUserId(userId: String): List<DailyNutrientTotal>
}
