package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.FoodMoodCorrelation
import com.example.The_Sanity_Line.demo.Repository.FoodMoodCorrelationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FoodMoodCorrelationService(private val repository: FoodMoodCorrelationRepository) {

    fun getAllByUser(userId: String): List<FoodMoodCorrelation> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun save(correlation: FoodMoodCorrelation): FoodMoodCorrelation =
        repository.save(correlation)
}
