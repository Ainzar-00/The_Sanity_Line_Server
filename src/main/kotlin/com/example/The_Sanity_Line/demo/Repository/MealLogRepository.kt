package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.MealLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MealLogRepository : JpaRepository<MealLog, String> {
    fun findAllByUserUserId(userId: String): List<MealLog>
    fun findAllByUserUserIdAndDate(userId: String, date: LocalDate): List<MealLog>
}
