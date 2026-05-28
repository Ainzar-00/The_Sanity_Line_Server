package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.FoodMoodCorrelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodMoodCorrelationRepository : JpaRepository<FoodMoodCorrelation, String> {
    fun findAllByUserUserId(userId: String): List<FoodMoodCorrelation>
}
