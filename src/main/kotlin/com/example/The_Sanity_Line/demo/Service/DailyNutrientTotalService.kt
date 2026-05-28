package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.DailyNutrientTotal
import com.example.The_Sanity_Line.demo.Repository.DailyNutrientTotalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class DailyNutrientTotalService(private val repository: DailyNutrientTotalRepository) {

    fun getByUserAndDate(userId: String, date: LocalDate): DailyNutrientTotal? =
        repository.findByUserUserIdAndDate(userId, date)

    fun getAllByUser(userId: String): List<DailyNutrientTotal> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun save(total: DailyNutrientTotal): DailyNutrientTotal =
        repository.save(total)
}
