package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.MealSuggestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MealSuggestionRepository : JpaRepository<MealSuggestion, String> {
    fun findAllByUserUserId(userId: String): List<MealSuggestion>
}
