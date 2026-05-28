package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.MealSuggestion
import com.example.The_Sanity_Line.demo.Repository.MealSuggestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MealSuggestionService(private val repository: MealSuggestionRepository) {

    fun getAllByUser(userId: String): List<MealSuggestion> =
        repository.findAllByUserUserId(userId)

    fun getById(id: String): MealSuggestion? =
        repository.findById(id).orElse(null)

    @Transactional
    fun save(suggestion: MealSuggestion): MealSuggestion =
        repository.save(suggestion)
}
