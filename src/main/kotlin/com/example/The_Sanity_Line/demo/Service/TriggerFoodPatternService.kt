package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.TriggerFoodPattern
import com.example.The_Sanity_Line.demo.Repository.TriggerFoodPatternRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TriggerFoodPatternService(private val repository: TriggerFoodPatternRepository) {

    fun getAllByUser(userId: String): List<TriggerFoodPattern> =
        repository.findAllByUserUserId(userId)

    fun getByUserAndFoodName(userId: String, foodName: String): TriggerFoodPattern? =
        repository.findByUserUserIdAndFoodName(userId, foodName)

    @Transactional
    fun save(pattern: TriggerFoodPattern): TriggerFoodPattern =
        repository.save(pattern)
}
