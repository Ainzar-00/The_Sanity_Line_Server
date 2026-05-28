package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.DailyState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyStateRepository : JpaRepository<DailyState, String> {
    fun findByUserUserIdAndDate(userId: String, date: LocalDate): DailyState?
    fun findAllByUserUserId(userId: String): List<DailyState>
}
