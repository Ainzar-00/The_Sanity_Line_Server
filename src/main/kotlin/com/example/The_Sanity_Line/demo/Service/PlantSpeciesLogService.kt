package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.PlantSpeciesLog
import com.example.The_Sanity_Line.demo.Repository.PlantSpeciesLogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class PlantSpeciesLogService(private val repository: PlantSpeciesLogRepository) {

    fun getByUserAndWeek(userId: String, weekStart: LocalDate): List<PlantSpeciesLog> =
        repository.findAllByUserUserIdAndWeekStart(userId, weekStart)

    @Transactional
    fun save(log: PlantSpeciesLog): PlantSpeciesLog =
        repository.save(log)
}
