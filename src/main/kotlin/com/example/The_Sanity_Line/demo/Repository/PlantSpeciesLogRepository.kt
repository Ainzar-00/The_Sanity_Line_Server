package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.PlantSpeciesLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface PlantSpeciesLogRepository : JpaRepository<PlantSpeciesLog, String> {
    fun findAllByUserUserIdAndWeekStart(userId: String, weekStart: LocalDate): List<PlantSpeciesLog>
}
