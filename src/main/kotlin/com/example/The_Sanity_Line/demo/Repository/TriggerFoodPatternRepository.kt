package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.TriggerFoodPattern
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TriggerFoodPatternRepository : JpaRepository<TriggerFoodPattern, String> {
    fun findAllByUserUserId(userId: String): List<TriggerFoodPattern>
    fun findByUserUserIdAndFoodName(userId: String, foodName: String): TriggerFoodPattern?
}
