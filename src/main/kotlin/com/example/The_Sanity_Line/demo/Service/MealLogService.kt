package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.MealLog
import com.example.The_Sanity_Line.demo.Repository.MealLogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class MealLogService(private val repository: MealLogRepository) {

    fun getAllByUser(userId: String): List<MealLog> =
        repository.findAllByUserUserId(userId)

    fun getByUserAndDate(userId: String, date: LocalDate): List<MealLog> =
        repository.findAllByUserUserIdAndDate(userId, date)

    @Transactional
    fun save(mealLog: MealLog): MealLog =
        repository.save(mealLog)

    @Transactional
    fun delete(id: String) =
        repository.deleteById(id)
}
